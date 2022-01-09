package net.linksguard.services;

 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.linksguard.dao.AvcmentFicheRepository; 
import net.linksguard.dao.FicheReporistory; 
import net.linksguard.dao.UserRepository;
import net.linksguard.dao.ServicePlateformeRepository;
import net.linksguard.dao.StatusFicheRepository; 
import net.linksguard.email.WSSendEmailAvcMent;
import net.linksguard.email.WSSendEmailFin; 
import net.linksguard.entities.EtaAvcMent;
import net.linksguard.entities.Fiche; 
import net.linksguard.entities.User;
import net.linksguard.entities.ServicesPlateformes;
import net.linksguard.entities.StatutFiche;
 
import net.linksguard.models.AccesModel;
import net.linksguard.models.AvctModel;
import net.linksguard.models.NotifModel;

@RestController
public class RestServices {
	 WSSendEmailAvcMent wSAvcMent = new WSSendEmailAvcMent();
	 WSSendEmailFin wbServiceFin = new WSSendEmailFin();
	@Autowired
	private FicheReporistory ficheReporistory;
	  
	 
	
	@Autowired
	private StatusFicheRepository statusFicheRepository;
	@Autowired
	private AvcmentFicheRepository avcmentFicheRepository;
	
	@Autowired
	private UserRepository personneRepository;
	
 
	
	@Autowired
	private ServicePlateformeRepository servicePlateformeRepository ;
	@RequestMapping("/searchPlatformAutocomplete")
	@ResponseBody
	public List<String> searchPlatform(@RequestParam(value="term", required=false, defaultValue="") String searchTerm) {
		List<String> suggestion = new ArrayList<String>();
		
		List<ServicesPlateformes> listServices = servicePlateformeRepository.findByNameContains(searchTerm);
		for (ServicesPlateformes service : listServices) {
			suggestion.add(service.getName().toString());
		}
		
		return suggestion;
	}
	
	@RequestMapping(value = "/addService",method = RequestMethod.POST)
	public boolean saveService(@RequestBody ServicesPlateformes servicesPlateformes) {
		if(servicePlateformeRepository.findByName(servicesPlateformes.getName()).size() > 0) {
			return false;
		}else {
			servicesPlateformes.setCreationDate(new Date());
			servicesPlateformes.setServiceClasse("GOLD");
			servicePlateformeRepository.save(servicesPlateformes);
		}
		return true;
	}
	
	
	@GetMapping("/listeFiche")
	public List<Fiche> findAllFicheOngoing(){
		StatutFiche openFiche =  statusFicheRepository.getOne(1L);
		return ficheReporistory.findByStatusFicheId(openFiche);
		
	}
	
	
 
	@GetMapping("/listeFiche/{id}")
	public List<Fiche> getFicheNEW(@PathVariable long id) {
		StatutFiche openFiche =  statusFicheRepository.getOne(1L);
		return ficheReporistory.findByStatusFicheIdAndId(openFiche,id);
	}
	
	@RequestMapping(value = "/addProfilAcces",method = RequestMethod.POST)
	public String addProfilAcces(@RequestBody AccesModel accesModel) {
		
		
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String result = "KO";
		/*
		if(!accesModel.getRoleAdmin().equals(0)&&!accesModel.getRoleUser().equals(0)) {
			List<Users> listUsers;
			
			listUsers = usersRepository.findByUsername(accesModel.getEmail());
			
			if(listUsers.size()> 0) {
				// si luser exist
				if(accesModel.getRoleAdmin().equals(1)) {
					
					if(usersRolesRepository.findCountRoles("ADMIN", accesModel.getEmail()) > 0) {
						result = "Ce user a déjà le droit admin";
						
						return result;
					}else {
						// il fo voir si il na pas deja le rol user
						if(usersRolesRepository.findCountRoles("USER", accesModel.getEmail()) > 0) {
							usersRolesRepository.save(new UsersRoles("ADMIN", accesModel.getEmail()));
							
							result = "Il avait deja le role User, rajout du Role Admin";
							return result;
						}else {
							
							usersRolesRepository.save(new UsersRoles("ADMIN", accesModel.getEmail()));
							usersRolesRepository.save(new UsersRoles("USER", accesModel.getEmail()));
							result = "Il n'avait aucun droit, ajout des Roles ADMIN et USER";
							return result;
						}
						
						
					}
					
					
					
				}
				
				if(accesModel.getRoleUser().equals(1)) {
					if(usersRolesRepository.findCountRoles("USER", accesModel.getEmail()) > 0) {
						
						result = "Ce user a déjà le role USER";
						
						return result;	
					}else {
						
						usersRolesRepository.save(new UsersRoles("USER", accesModel.getEmail()));
						result = "Il n'avait aucun droit, ajout du role USER";
						return result;
						
					}
					
				}
				
				
			}else {
				 // Si luser nexiste pas 
				if(accesModel.getRoleAdmin().equals(1)) {
					usersRepository.save(new Users(accesModel.getEmail(), bcpe.encode("SUper2019$"), true));
					usersRolesRepository.save(new UsersRoles("ADMIN", accesModel.getEmail()));
					usersRolesRepository.save(new UsersRoles("USER", accesModel.getEmail()));
					result = "L'utilisateur n'existait pas, creation et ajout des roles ADMIN et USER";
					}else if(accesModel.getRoleUser().equals(1)){
						usersRepository.save(new Users(accesModel.getEmail(), bcpe.encode("SUper2019$"), true));
					 
						usersRolesRepository.save(new UsersRoles("USER", accesModel.getEmail()));
						result = "L'utilisateur n'existait pas, creation et ajout du roles USER";
							
						
					}
				
			}
		}else {
			
			result = "Retrait des Roles d'acces";
			
			
			
		}
		
		
		
		 */
		return result;
	}
	
	
	@Transactional
	@RequestMapping(value = "/notifSubscribe",method = RequestMethod.POST)
	public boolean nofitication(@RequestBody NotifModel notifMadel) {
		User mPersonne = new User();
		ServicesPlateformes  mServicesPlateformes = new ServicesPlateformes(); 
		mServicesPlateformes = servicePlateformeRepository.getOne(notifMadel.getServiceID());
		mPersonne = personneRepository.getOne(notifMadel.getUserID()); 
		mPersonne.getSerPlateNotif().add(mServicesPlateformes);
		personneRepository.save(mPersonne);
		return true;
	}
	
	
	

	@RequestMapping(value = "/etatAvancement",method = RequestMethod.POST)
	public boolean saveEtatAvancement(@RequestBody AvctModel etaAvcMentModel) { 
		ServicesPlateformes mServicesPlateformes;
		 
		 try {
			 Fiche mFiche = ficheReporistory.getOne(etaAvcMentModel.getAvcFichId());
			 
			 mServicesPlateformes = servicePlateformeRepository.getOne(mFiche.getServiceID());
			  
			mServicesPlateformes.getUser().forEach(e -> wSAvcMent.sendMailWebService(e.getEmail(), "Etat d'avencement sur "+mServicesPlateformes.getName(),mServicesPlateformes.getName(),etaAvcMentModel.getLibelle(), "xxxxxxxxxx") );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		avcmentFicheRepository.save(new EtaAvcMent(etaAvcMentModel.getLibelle(), ficheReporistory.getOne(etaAvcMentModel.getAvcFichId()), new Date()));
		return true;
	}
	
	@Transactional
	@RequestMapping(value = "/clotureFiche",method = RequestMethod.POST)
	public boolean clotureFiche(@RequestBody AvctModel etaAvcMentModel) {
		
		//Fiche mFiche = ficheReporistory.getOne(etaAvcMentModel.getAvcFichId());
		ServicesPlateformes mServicesPlateformes;
	 
		 try {
			 Fiche mFiche = ficheReporistory.getOne(etaAvcMentModel.getAvcFichId());
			 mFiche.setDateFin(new Date());
			  mFiche.setStatusFicheId(statusFicheRepository.getOne(2L));
			 ficheReporistory.save(mFiche);
			 mServicesPlateformes = servicePlateformeRepository.getOne(mFiche.getServiceID());
			  
			mServicesPlateformes.getUser().forEach(e -> wbServiceFin.sendMailWebService(e.getEmail(), "Côture de la fiche sur "+mServicesPlateformes.getName(), mServicesPlateformes.getName(),
					etaAvcMentModel.getLibelle(),"XXXX") );
			
			 
			
		//	ficheReporistory.delete(mFiche);
		 
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		avcmentFicheRepository.save(new EtaAvcMent(etaAvcMentModel.getLibelle(), ficheReporistory.getOne(etaAvcMentModel.getAvcFichId()), new Date()));
		return true;
	}
	
	
	
	
	

}
