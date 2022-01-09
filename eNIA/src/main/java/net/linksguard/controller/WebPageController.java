package net.linksguard.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.lowagie.text.DocumentException;

import net.linksguard.dao.EquipeRepository;
import net.linksguard.dao.FicheReporistory;
import net.linksguard.dao.KBcaseRepository;
import net.linksguard.dao.UserRepository;
import net.linksguard.dao.ServicePlateformeRepository;
import net.linksguard.dao.StatusFicheRepository;
import net.linksguard.dao.SurveyCustomerRepository;
import net.linksguard.email.WSSendEmailSurvey; 
import net.linksguard.entities.Equipes;
import net.linksguard.entities.Fiche; 
import net.linksguard.entities.User;
import net.linksguard.entities.ServicesPlateformes;
import net.linksguard.entities.StatutFiche;
import net.linksguard.entities.SurveyCustomer;
import net.linksguard.excelexporter.FicheExporter;
import net.linksguard.models.IssuesModel;
import net.linksguard.models.SurveyModel;
import net.linksguard.pdfexporter.ListeOngoing;

@Controller
public class WebPageController {
	@Autowired
	private FicheReporistory ficheReporistory;
	@Autowired
	private KBcaseRepository kBcaseRepository;
	//WebServiceSendEmail webServiceSendMail = new WebServiceSendEmail();
	
	WSSendEmailSurvey wSSendEmailSurvey = new WSSendEmailSurvey();
	@Autowired
	private StatusFicheRepository statusFicheRepository;
	@Autowired
	private UserRepository personneRepository;
	@Autowired
	private ServicePlateformeRepository servicePlateformeRepository ;
	@Autowired
	private EquipeRepository equipeRepository; 
	@Autowired
	private SurveyCustomerRepository surveyCustomerRepository; 
	
	
	@GetMapping({ "/", "/index" })
	public String indexPage(Model model, HttpServletResponse request) {
		
		/*
		Locale currentLocal = request.getLocale();
		String countryCode = currentLocal.getCountry();
		String countryName = currentLocal.getDisplayCountry();
		String langCode = currentLocal.getLanguage();
		String langName = currentLocal.getDisplayLanguage();
		
		System.out.println(countryCode+ " "+countryName);
		
		System.out.println(langCode+ " "+langName);
		System.out.println("-----------------------------------");
		String [] languages = Locale.getISOLanguages();
		
		for (String language : languages) {
			Locale local = new Locale(language);
			System.out.println(language+ " "+local.getDisplayLanguage());
			
		}
		*/
		StatutFiche openFiche =  statusFicheRepository.getOne(1L);
		Page<Fiche> listFiches = ficheReporistory.findByStatusFicheId(openFiche,PageRequest.of(0, 6,Sort.by("creationDate").descending()));
		long totalPages = ficheReporistory.count();
		long totalFI = ficheReporistory.findTypeFiche("FI");
		long totalFP = ficheReporistory.findTypeFiche("FP");
		
		 
		model.addAttribute("listFiches", listFiches);
		model.addAttribute("totalFI", totalFI);
		model.addAttribute("totalFP", totalFP);
		model.addAttribute("totalPages", totalPages);	
		return "index";
	}
	
	
	
	@GetMapping("/user/adminDashBoard")
	public String adminDashBordPage() {
		return "admPage";
	}
	
	@GetMapping("/user/dashboard")
	public String Dashboard() {
		return "dashboard";
	}
	
	
	@GetMapping("/admin/dataImport")
	public String StratJon() {
		return "dataImport";
	}
	
	
	@GetMapping("/admin/createTicket")
	public String createTicket() {
		return "createTicket";
	}
	
	
	@GetMapping("/admin/statSurvey")
	public String statSurvey(HttpServletRequest request, Model model) {
		StatutFiche closedFiche =  statusFicheRepository.getOne(2L);
		
	     int page = 0; //default page number is 0 (yes it is weird)
	     /*
	        int size = 10; //default page size is 10
	        String motCl = "";
	        
	        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }

	        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
	        if (request.getParameter("motCle") != null && !request.getParameter("motCle").isEmpty()) {
	        	motCl = request.getParameter("motCle");
	        }
	        */
	        model.addAttribute("listFiches",ficheReporistory.findByStatusFicheId(closedFiche, PageRequest.of(page, 5,Sort.by("creationDate").descending())));
		
		return "statSurveyFicheList";
	}
	
	
	@RequestMapping("/userSurveySubmit")
	public String userSurveySubmit(@RequestParam(name="idPersonne") int idPersonne,@RequestParam(name="idIssue") int idIssue,@RequestParam(name="qest1Radios") int qest1Radios,@RequestParam(name="qest2Radios")  int qest2Radios ) {
		//System.out.println("************************ " +idIssue);
		//System.out.println("************************ " +surveyModel.getQest1Radios() );
		 
		surveyCustomerRepository.save(new SurveyCustomer(qest1Radios, qest2Radios, idIssue, ""));
		return "userSurveyResult";
	}
	
	
	@GetMapping("/userSurvey")
	public String userSurvey(Model model, long uId, long isId, String period) {
		
		model.addAttribute("period", period);
		model.addAttribute("isId", isId);
		model.addAttribute("uId", uId);
		model.addAttribute("surveyModel", new SurveyModel());
		return "userSurvey";
	}
	
	
	@GetMapping("/user/listeOngoing")
	public String listeOngoing(Model model,
		    @RequestParam(name="page", defaultValue = "0") int page) {
		Page<Fiche> listFiches = ficheReporistory.findAll(PageRequest.of(page, 5,Sort.by("creationDate").descending()));
		ServicesPlateformes mServicesPlateformes;	 
		 
		
		model.addAttribute("listFiches", listFiches);
		model.addAttribute("pages", new int[listFiches.getTotalPages()]);
		model.addAttribute("currentPage", page);	
		
		return "listeOngoing";
	}
	 @GetMapping("/user/listeKDb")
	    public String customersPage(HttpServletRequest request, Model model) {
	        
	        int page = 0; //default page number is 0 (yes it is weird)
	        int size = 10; //default page size is 10
	        String motCl = "";
	        
	        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }

	        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
	        if (request.getParameter("motCle") != null && !request.getParameter("motCle").isEmpty()) {
	        	motCl = request.getParameter("motCle");
	        }
	        
	        model.addAttribute("listKBcase", kBcaseRepository.findByDescriptionLabelContains(motCl, PageRequest.of(page, size)));
	        return "listeKbd";
	    }
	 
	 @GetMapping("/admin/surveyCustomerStart")
		public String surveyCustomerStart(Model model,long id) {
		 ServicesPlateformes mServicesPlateformes;	 
		 
		 Fiche fiche = ficheReporistory.getOne(id);
		 
		 try {
			 mServicesPlateformes = servicePlateformeRepository.getOne(fiche.getServiceID());
			 
			 mServicesPlateformes.getUser().forEach(e -> wSSendEmailSurvey.sendMailWebService(e.getId(),fiche.getId(), "xxxx",e.getEmail(),"Enquete de statifcaction client l'incident sur "+mServicesPlateformes.getName(),mServicesPlateformes.getName(), "XXXXXXXXX") );
		
		 
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 
		 
		 
		 model.addAttribute("fiche", fiche);
			 
			 
			 
			 
			return "surveyFiche";
		}
	 
	 
	 
	 @GetMapping("/admin/oneSurveyResultView")
		public String surveyStatOneViewFiche(Model model,long id) {
		  Fiche  fiche = ficheReporistory.getOne(id);
		 List<SurveyCustomer> responseList =  surveyCustomerRepository.findByIssueId(id);
		 int listSize = responseList.size();
		 
		 int tressatisf = 0,satsifai =0, neutre = 0, insatisf = 0, tresinst = 0, tauxSatisfact = 0;
		 
		 for (SurveyCustomer response : responseList ) {
		 
			 switch(response.getQest1answ()) {
			   case 10 :
				   tressatisf++;
			      break; // optional
			   
			   case 7 :
				   satsifai++;
			      break; // optional
			   case 5 :
				   neutre++;
				 break; // optional
			   case 3 :
				   insatisf++;
				      break; // optional
			   case 1 :
				   tresinst++;
				      break; // optional
			   // You can have any number of case statements.
			   default : // Optional
			      // Statements
			}
		}
		 tauxSatisfact = (tressatisf*10 + satsifai*7 + neutre*5+ insatisf*3+ tresinst*1)*100/(listSize*10);
		 model.addAttribute("tressatisf", tressatisf*100/listSize);
		 model.addAttribute("satsifai", satsifai*100/listSize);
		 model.addAttribute("neutre", neutre*100/listSize);
		 model.addAttribute("insatisf", insatisf*100/listSize);
		 model.addAttribute("tresinst", tresinst*100/listSize);
		 model.addAttribute("titre", fiche.getTitre());
		 model.addAttribute("startDate", fiche.getCreationDate());
		 model.addAttribute("endDate", fiche.getDateFin());
		 model.addAttribute("nbClientSurvey", listSize);
		 model.addAttribute("tauxSatisfact", tauxSatisfact);
			return "surveyStatOneViewFiche";
		}
	 
	 
	  
	 
	@GetMapping("/admin/surveyCustomer")
	public String surveyCustomer(HttpServletRequest request, Model model) {
		StatutFiche closedFiche =  statusFicheRepository.getOne(2L);
		
	     int page = 0; //default page number is 0 (yes it is weird)
	     /*
	        int size = 10; //default page size is 10
	        String motCl = "";
	        
	        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
	            page = Integer.parseInt(request.getParameter("page")) - 1;
	        }

	        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
	            size = Integer.parseInt(request.getParameter("size"));
	        }
	        if (request.getParameter("motCle") != null && !request.getParameter("motCle").isEmpty()) {
	        	motCl = request.getParameter("motCle");
	        }
	        */
	        model.addAttribute("listFiches",ficheReporistory.findByStatusFicheId(closedFiche, PageRequest.of(page, 5,Sort.by("creationDate").descending())));
		return "listCustomerSurvey";
	}
	
	
	@GetMapping("/user/pdfexport")
	public void pdfexport(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf"); 
		DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateformatter.format(new Date());
		String fileName = "listeFiches"+currentDateTime+".pdf";
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename="+fileName;
		response.setHeader(headerKey, headerValue);
		List<Fiche> listFiches = ficheReporistory.findAll();
		
		ListeOngoing  listeOnGoingPDF = new ListeOngoing(listFiches);
		listeOnGoingPDF.export(response);
		
	}
	
	@GetMapping("/user/csvexport")
	public void csvexport(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		
		DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateformatter.format(new Date());
		String fileName = "listeFiches"+currentDateTime+".csv";
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename="+fileName;
		response.setHeader(headerKey, headerValue);
		
		List<Fiche> listFiches = ficheReporistory.findAll();
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		
		String[] csvHeader = {"Fiche ID", "Titre","Date"};
		String[] nameMapping = {"id","titre","creationDate"};
		csvWriter.writeHeader(csvHeader);
		
		for (Fiche fiche : listFiches) {
			csvWriter.write(fiche, nameMapping);
			
		}
		csvWriter.close();
		
	 
		
	}
	@GetMapping("/user/excelexport")
	public void excelexport(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		
		String headerKey = "Content-Disposition";
		
		DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateformatter.format(new Date());
		String fileName = "listeFiches"+currentDateTime+".xlsx";
		String headerValue = "attachement; filename="+fileName;
		response.setHeader(headerKey, headerValue);
		List<Fiche> listFiches = ficheReporistory.findAll();
		
		FicheExporter fichesExportExcel = new FicheExporter(listFiches);
		fichesExportExcel.export(response);
		
		
	}
	
	@GetMapping("/admin/clotureFicheWb")
	public String clotureFicheWb(Model model,long id) {
		model.addAttribute("fiche", ficheReporistory.getOne(id));
		return "clotureFiche";
	}
	@GetMapping("/user/kbdDetail")
	public String kbdDetail(Model model,long id) {
		model.addAttribute("kdbDetail", kBcaseRepository.getOne(id));
		return "kbdDetail";
	}
	
	/*
	@GetMapping("/user/listeKDb")
	public String listeKDb(Model model, @RequestParam(name="page", defaultValue = "1") Optional<Integer> page
	    ,@RequestParam(name="motCle", defaultValue = "")  String mc, 
			      @RequestParam("size") Optional<Integer> size) {
		
		int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
		
			Page<KBcase> listKBcase = kBcaseRepository.findByDescriptionLabelContains(mc, PageRequest.of(currentPage - 1, pageSize));
			long totalItems = listKBcase.getTotalElements();
			model.addAttribute("listKBcase", listKBcase);
			int totalPages = listKBcase.getTotalPages();
			
			if (totalPages > 0) {
			    List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
			        .boxed()
			        .collect(Collectors.toList());
			    model.addAttribute("pageNumbers", pageNumbers);
			}
			//model.addAttribute("totalPages",totalPages);
			model.addAttribute("totalItems",totalItems);
			model.addAttribute("motCle",mc);
			
			
			model.addAttribute("pages", new int[listKBcase.getTotalPages()]);
			model.addAttribute("currentPage", page);
			
		
		
		return "listeKbd";
	}
	*/
	
	
	// date format to use for display timestamp
    private SimpleDateFormat format = new SimpleDateFormat("EEEE MMMM d, y - hh:mm:ss aa");
	@GetMapping("/user/addFile")
	public String addFile() {
		return "uploadFile";
	}
	@GetMapping("/info")
	public String help() {
		return "help";
	}
	
	
	
	@GetMapping("/user/addKDb")
	public String addKDb(Model model) {
		return "nCreateKB";
	}
	
	
	
	
	
	
	@GetMapping("/user/searchPersonne")
	public String searchPersonne(Model model,
		    @RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="motCle", defaultValue = "")  String mc) {
		Page<User> listPersonne = personneRepository.findByEmailContains(mc, PageRequest.of(page, 5));
		
		
		model.addAttribute("listPersonne", listPersonne);
		model.addAttribute("pages", new int[listPersonne.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("motCle",mc);
		
		return "searchPersonne";
	}
	
 
	@GetMapping("/user/searchPlateforme")
	public String searchPlateforme(Model model,
		    @RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="motCle", defaultValue = "")  String mc) {
	 
		Page<ServicesPlateformes> listServices = servicePlateformeRepository.findByNameContains(mc, PageRequest.of(page, 5));
		
		
		model.addAttribute("listServices", listServices);
		model.addAttribute("pages", new int[listServices.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("motCle",mc);
		
		return "searchServices";
	}
 
	
	@GetMapping("/user/searchTeam")
	public String searchTeam(Model model,
		    @RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="motCle", defaultValue = "")  String mc) {
		Page<Equipes> listTeam = equipeRepository.findByNomContains(mc, PageRequest.of(page, 5));
		
		
		model.addAttribute("listTeam", listTeam);
		model.addAttribute("pages", new int[listTeam.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("motCle",mc);
		
		return "searchTeam";
	}
	
	
	
	
	@GetMapping("/admin/searchNotifPersonne")
	public String searchNotifPersonne(Model model,
		    @RequestParam(name="page", defaultValue = "0") int page,
			@RequestParam(name="motCle", defaultValue = "")  String mc) {
		Page<User> listPersonne = personneRepository.findByEmailContains(mc, PageRequest.of(page, 5));
		
		
		 model.addAttribute("listePersonne", listPersonne);
		model.addAttribute("pages", new int[listPersonne.getTotalPages()]);
		model.addAttribute("currentPage", page);
		return "searchNotifPersonne";
	}
	 
	@GetMapping("/admin/listeNotifDetail")
	public String listeNotifDetail(Model model,long id) {
		model.addAttribute("personneNotDetail", personneRepository.getOne(id));
		return "listeNotifDetail";
	}
	 
	@GetMapping("/admin/addAccess")
	public String adminAcces(Model model,
		    @RequestParam(name="page", defaultValue = "0") int page) {
		
		Page<User> listePersonne =personneRepository.findAll(PageRequest.of(page, 5));
		model.addAttribute("listePersonne", listePersonne);
		model.addAttribute("pages", new int[listePersonne.getTotalPages()]);
		model.addAttribute("currentPage", page);
		return "listePersonneAcces";
	}
	
	@GetMapping("/admin/addTeam")
	public String addteam() {
		return "addTeam";
	}
	
	 
	
	
	@GetMapping("/admin/etatAvcment")
	public String addAvcMent(Model model,long id) {
		model.addAttribute("fiche", ficheReporistory.getOne(id));
		return "addAvcFiche";
	}
	
	
	
	
	@GetMapping("/admin/addAvancIncident")
	public String addAvancIncident(Model model,
		    @RequestParam(name="page", defaultValue = "0") int page) {
		StatutFiche openFiche =  statusFicheRepository.getOne(1L);
		Page<Fiche> listFiches = ficheReporistory.findByStatusFicheId(openFiche,PageRequest.of(page, 5));
		
		model.addAttribute("listFiches", listFiches);
		model.addAttribute("pages", new int[listFiches.getTotalPages()]);
		model.addAttribute("currentPage", page);
		return "listeFiche";
	}
	@GetMapping("/admin/addPlateforme")
	public String addPlateforme() {
		return "addApplication";
	}
	
	//
	@GetMapping("/admin/ticket")
	public String indexTicket(Model model) {
		model.addAttribute("title", "IMS - Home");
        model.addAttribute("date", format.format(new Date()));
        return "ticket/index";
	}
	
	@GetMapping("/admin/user/add")
	public String adminUser(Model model) {
		model.addAttribute("title", "IMS - Home");
        model.addAttribute("date", format.format(new Date()));
        return "admin/user";
	}
	
	
	@GetMapping("/403")
	public String notAuthorz() {
		 
		return "n403";
	}
	@GetMapping("/admin/associer")
	public String associer(Model model,long id, String email) {
List<ServicesPlateformes> servicesList = new ArrayList<ServicesPlateformes>();
		
servicesList = servicePlateformeRepository.findAll();
		
		model.addAttribute("idPersonne", id);
		model.addAttribute("emailPersonne", email);
		model.addAttribute("servicesList", servicesList);
		return "subscriberPer";
	}
	
	
	@GetMapping("/admin/accesProfil")
	public String accesProfil(Model model,long id, String email, Integer acces) {
 
		
		model.addAttribute("idPersonne", id);
		model.addAttribute("accesPersonne", acces);
		model.addAttribute("emailPersonne", email); 
		return "addAcces";
	}
	
	
	
	@GetMapping("/admin/listePersonne")
	public String listePersonne(Model model,
		    @RequestParam(name="page", defaultValue = "0") int page) {
		
		Page<User> listePersonne =personneRepository.findAll(PageRequest.of(page, 5));
		model.addAttribute("listePersonne", listePersonne);
		model.addAttribute("pages", new int[listePersonne.getTotalPages()]);
		model.addAttribute("currentPage", page);
		return "listePersonne";
	}
	 
	@GetMapping("/admin/addPersonne")
	public String addPersonne(Model model) {
		List<Equipes> equipesList = new ArrayList<Equipes>();
		
		equipesList =  equipeRepository.findAll();
	
		model.addAttribute("equipesList", equipesList);
		
		return "addPersonne";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	

	
	
	@GetMapping("/admin/addFiche")
	public String loadFiche(Model model) {
		List<Equipes> equipesList = new ArrayList<Equipes>();
		List<ServicesPlateformes> servicesPlateformesList = new ArrayList<ServicesPlateformes>();
		
		servicesPlateformesList = servicePlateformeRepository.findAll();
		equipesList =  equipeRepository.findAll();
		model.addAttribute("equipesList", equipesList);
		model.addAttribute("servicesPlateformesList", servicesPlateformesList);
		return "addFiche";
	}
	 

}
