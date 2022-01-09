package net.linksguard.ims.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import net.linksguard.entities.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails extends User implements UserDetails {

	public CustomUserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
	}

	@Override
	public String getUsername() {
	 
		return super.getEmail();
	}

	@Override
    public String getPassword() {
        return super.getPassword();
    }

	
	@Override
	public boolean isAccountNonExpired() {
		 
		return false;
	}
	@Override
	public Long getId() { return super.getId(); }

	@Override
	public boolean isAccountNonLocked() {
		 
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		 
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	 // get the group id of the user
    public ImsAssignedGroup getGroup(){ return  getGroupId(); }
}
