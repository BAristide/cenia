package net.linksguard.email;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

public class WebServiceSendEmail {
	
	
	
	  public  void sendMailWebService(String recepient, String object, String emailSubject ) { 
	      
		  sendMailNoProxyFromLinksGuardDomaine(recepient, object,emailSubject );
	    }
	
	
	  
	  
	  
	    private void sendMailNoProxyFromLinksGuardDomaine(String recepient, String object, String emailSubject ) {
	    	   
            Email email = EmailBuilder.startingBlank()
          .from("eNIA | LinksGuard", "alert@linksguard.net")
          .to("AlertRecepient", recepient)
        //  .withEmailAddressCriteria(EmailAddressCriteria.DEFAULT)      
          .withSubject(object)
          .withHTMLText(emailSubject) 
          .buildEmail();
          
          
          
          
          
          
          Mailer mailer = MailerBuilder
    .withSMTPServer("mail41.lwspanel.com",465, "alert@linksguard.net", "wA3!HHC_Xg")
          .withTransportStrategy(TransportStrategy.SMTPS)
       
       
       //   .withProperty("mail.smtp.sendpartial", true)
        // .withDebugLogging(true)
          .buildMailer();

mailer.sendMail(email);
          
 

System.out.println("Message sent...");
    }

}