package net.linksguard.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.linksguard.dao.UserRepository; 
import net.linksguard.entities.User; 
import net.linksguard.models.UserRegistrationDto; 

@RestController
public class PersonneRestService {
	@Autowired
	private UserRepository personneRepository;
	@Autowired
	private PersonneService personneService;
	
	//@Autowired
	//private UsersRepository usersRepository;
	
	 
	//private UsersRolesRepository usersRolesRepository;
	
	@RequestMapping(value = "/personne",   method = RequestMethod.POST)
	public boolean savePersonne(@RequestBody User personne) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		List<User> listPersonne;
		
		UserRegistrationDto newUser = new UserRegistrationDto(personne.getLastName(),
				personne.getFirstName(), personne.getMatricule(), personne.getEmail(), personne.getPassword());
		
		 
		listPersonne = personneRepository.findByEmail(personne.getEmail());
		
		if(listPersonne.size()> 0) {
			return false;
		}else {
			personneService.save(newUser);
			//personne.setCreationDate(new Date());
			//personneRepository.save(personne);
			/*
			
			if(personne.getAcces().equals(1)) {
			usersRepository.save(new Users(personne.getEmail(), bcpe.encode("SUper2019$"), true));
			 usersRolesRepository.save(new UsersRoles("USER", personne.getEmail()));
			
			
		}
		*/
		}

		return true;
	}
	
	
	
	

}
