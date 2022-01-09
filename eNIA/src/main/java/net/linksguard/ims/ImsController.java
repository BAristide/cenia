package net.linksguard.ims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import net.linksguard.ims.dao.GroupDao;
import net.linksguard.ims.entities.CustomUserDetails;
import net.linksguard.ims.entities.ImsAssignedGroup;
import net.linksguard.dao.UserRepository;
import net.linksguard.entities.User;

import javax.servlet.http.HttpServletRequest;
 
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Michael DeAngelo
 * last update date: Aug 15, 2018
 * purpose: Controller class for the web root and other miscellaneous request mappings.  Mappings in this class include
 * document root, /profile, /password-change, /profile-update, /403 (for custom access denied page)
 */
@Controller
public class ImsController {

    @Autowired
    private UserRepository userDao;

   

    @Autowired
    private GroupDao groupDao;

    // date format to use for display timestamp
    private SimpleDateFormat format = new SimpleDateFormat("EEEE MMMM d, y - hh:mm:ss aa");

 

    /**
     * This method supplies the request mapping for the document root
     * @param model used to supply attributes to the view
     * @return view template
     */
    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "IMS - Home");
        model.addAttribute("date", format.format(new Date()));
        return "ticket/index";
    }

    /**
     * This method supplies the request mapping for the GET request method for the profile page which displays 2 forms.
     * The first form allows users to change their password and the second allows users to update their mobile phone
     * number and carrier.
     * @param customUserDetails  used to get detail about the logged in user
     * @param model used to supply attributes to the view
     * @return profile view template
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String displayProfileUpdate(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        model.addAttribute("title", "IMS - Update Profile");
        model.addAttribute("subtitle", "Update Profile");
        model.addAttribute("date", format.format(new Date()));
        model.addAttribute("user", userDao.findById(customUserDetails.getId()));
        
        return "/profile/profile";
    }

    /**
     * This method supplies the POST request mapping to process the password change
     * @param user User object that is being updated
     * @param customUserDetails used to get detail about the logged in user
     * @param model used to supply attributes to the view
     * @param message RedirectAttributes used to add a flash message for successful password change
     * @return profile view template if error is present, otherwise redirect to /ticket/main
     */
    @RequestMapping(value = "/password-change", method = RequestMethod.POST)
    public String processUpdatePassword(@ModelAttribute User user,
                                        @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                        Model model, RedirectAttributes message) {

        // use BCrypt to encode the password
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(11);

        // check to ensure the user entered all password fields
        if (user.getNewPassword().equals("") || user.getVerifyPassword().equals("") || user.getPassword().equals("")) {

            message.addFlashAttribute("message","All password fields are required");
            return "redirect:/profile";

        // check if the new password matches the verify field and the user entered the correct current password
        }else if ((!user.getNewPassword().equals(user.getVerifyPassword())) ||
                (!bCrypt.matches(user.getPassword(), customUserDetails.getPassword())) ) {

            message.addFlashAttribute("message", "Passwords do not match or invalid current password");
            return "redirect:/profile";

            // all checks passed, proceed with password change, update password in the database and set the success message
        }else {
            userDao.updatePasswordById(bCrypt.encode(user.getNewPassword()), customUserDetails.getId());
            message.addFlashAttribute("message", "Successfully changed password");

            return "redirect:/ticket/main";
        }
    }

    /**
     * This method supplies the POST request mapping to process the mobile phone and carrier update
     * @param user User object that is being updated
     * @param customUserDetails used to get detail about the logged in user
     * @param model used to supply attributes to the view
     * @param message RedirectAttributes used to add a flash message for successful password change
     * @return profile view template if error is present, otherwise redirect to /ticket/main
     */
    @RequestMapping(value = "/profile-update", method = RequestMethod.POST)
    public String processUpdateProfile(@ModelAttribute User user, Model model, RedirectAttributes message,
                                       @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        if (user.getPhone().isEmpty() || user.getPhone().length()!=10) {

            message.addFlashAttribute("message", "Phone number must be 10 digits and both carrier and phone are required");
            return "redirect:/profile";
        }

        // all required fields are present and pass validation, process the change and set the success message
        userDao.updatePhoneById(user.getPhone(),   customUserDetails.getId());
        message.addFlashAttribute("message", "Updated profile");

        return "redirect:/ticket/main";
    }

    /**
     * This method supplies the request mapping for the custom access denied error page
     * @return 403 view template
     */
    @RequestMapping("/403")
    public String accessDenied() {
        return "error/403";
    }


    /**
     * This method supplies the request mapping to display the sms messaging form
     * @param model used to supply attributes to the view
     * @return sms messaging form template view
     */
    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    public String displayMessaging(Model model) {
        model.addAttribute("title", "IMS - Messaging");
        model.addAttribute("subtitle", "SMS Messaging");
        model.addAttribute("date", format.format(new Date()));
       // model.addAttribute("message", new Message());
        model.addAttribute("groups", groupDao.findAll());
        model.addAttribute("users", userDao.findAllByOrderByLastName());
        return "sms";
    }

   
}