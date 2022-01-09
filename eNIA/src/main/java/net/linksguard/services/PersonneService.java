package net.linksguard.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.linksguard.entities.User;
import net.linksguard.models.UserRegistrationDto;

public interface PersonneService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

}
