package net.linksguard.services;

import java.util.Date;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import net.linksguard.dao.UserRepository;
 
import net.linksguard.entities.User;
import net.linksguard.entities.Roles;
 
import net.linksguard.models.UserRegistrationDto;
@Service
public class PersonneServiceImpl  implements PersonneService {

private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public PersonneServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(UserRegistrationDto registrationDto) {
		
		User user = new User();
		/*
		User user = new User(registrationDto.getLastName(), 
				registrationDto.getFirstName(), registrationDto.getMatricule(), 
				registrationDto.getEmail(), 
				passwordEncoder.encode(registrationDto.getPassword()), 
				Arrays.asList(new Roles("USER")), new Date());
		 */
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	
		User user = userRepository.getPersonneByUserEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
