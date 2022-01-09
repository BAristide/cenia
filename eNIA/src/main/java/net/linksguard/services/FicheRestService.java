package net.linksguard.services;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.linksguard.dao.ServicePlateformeRepository;
import net.linksguard.dao.StatusFicheRepository;
import net.linksguard.email.WSSendEmailDebutIncident; 
import net.linksguard.entities.Fiche; 
import net.linksguard.entities.ServicesPlateformes;
import net.linksguard.entities.StatutFiche;
import net.linksguard.firebasemessaging.FirebaseMessaging;
import net.linksguard.metier.FicheMetier;

@RestController
public class FicheRestService {
	WSSendEmailDebutIncident wSdebutIncident = new WSSendEmailDebutIncident();
	
	FirebaseMessaging myFireBase = new FirebaseMessaging();
	 
	@Autowired
	private ServicePlateformeRepository servicePlateformeRepository ;
	@Autowired
	private StatusFicheRepository statusFicheRepository;
	
	@Autowired
	private FicheMetier ficheMetier;
@RequestMapping(value = "/fiche", method = RequestMethod.POST)
	public boolean saveFiche(@RequestBody Fiche fiche) {  
	ServicesPlateformes mServicesPlateformes;
	if(statusFicheRepository.findAll().size() == 0) {
		
		statusFicheRepository.save(new StatutFiche("Open"));
		statusFicheRepository.save(new StatutFiche("Closed"));
	}
	 try {
		 mServicesPlateformes = servicePlateformeRepository.getOne(fiche.getServiceID());
		  
		mServicesPlateformes.getUser().forEach(e -> wSdebutIncident.sendMailWebService(e.getEmail(),
				"Ouverture fiche incident sur "+mServicesPlateformes.getName(),mServicesPlateformes.getName()
				,fiche.getTitre(),fiche.getDescription(),"") );
		
		System.out.println("***********  FIRE BASE START****************");
		
		myFireBase.sendFireBaseMessaging("NOUS AVONS UN INCIDENT", "NOUS INFORMONS DE LE SURVENU DU INCIDENT");
		System.out.println("***********  FIRE BASE END****************");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 
	 
	 fiche.setStatusFicheId(statusFicheRepository.getOne(1L));
	 
	ficheMetier.saveFiche(fiche);
		return true;
	}
@RequestMapping(value = "/fiche/{id}", method = RequestMethod.GET)
	public Fiche getFicheC(@PathVariable Long id) {
		return ficheMetier.getFicheC(id);
	}

}
