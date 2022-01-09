package net.linksguard.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.linksguard.services.PersonneService;
 

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Autowired
	private PersonneService userService;
	
	/*
	
	@Bean
	public UserDetailsService  userDetailsService() {
		return new UserDetailServiceImpl();
	}
	
	*/
	@Autowired
	private DataSource dataSource;
	// Pour specifier ou son  stocket les utilisateur
	
	
	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.authenticationProvider(authenticationProvider());
		 //noop ces pour eviter que spring encrip avant de conparer le problem est que le passw reste en clair dans la ram
		 
		
		 BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		
		 System.out.println("************* admin password: "+bcpe.encode("SUper2019$"));
		 System.out.println("VOTRE MO DE PASS User"+bcpe.encode("SUper2019$"));
		 /*
   
		 auth.jdbcAuthentication().dataSource(dataSource)
		 	.usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
		 	.authoritiesByUsernameQuery("select username as principal, roles as role from users_roles where username=?")
		 	.rolePrefix("ROLE_")
		 	.passwordEncoder(getBCPE());
		 */
		 
	 }
	 @Override
		protected void configure(HttpSecurity http) throws Exception {
			http.formLogin().loginPage("/login");
			http.authorizeRequests().antMatchers("/admin/*").hasAnyAuthority("ADMIN", "USER");
			http.authorizeRequests().antMatchers("/user/*").hasAnyAuthority( "USER") ; 
		       
			http.exceptionHandling().accessDeniedPage("/403");
            // require user or support to access these pages
			http.authorizeRequests().antMatchers("/ticket/view/**").hasAnyAuthority("USER", "SUPPORT");
			http.authorizeRequests().antMatchers("/ticket/create").hasAnyAuthority("USER", "SUPPORT");

        // require support role to have access to all tickets
			http.authorizeRequests().antMatchers("/ticket/list/2").hasAnyAuthority("SUPPORT");

			// require admin role to have access to any of the admin pages
			http.authorizeRequests().antMatchers("/imsadmin/**").permitAll(); 
			http.authorizeRequests().antMatchers("/index").permitAll(); 
			http.authorizeRequests().antMatchers("/userSurvey").permitAll(); 
			http.authorizeRequests().antMatchers("/info").permitAll(); 
			http.authorizeRequests().and().logout().logoutUrl("/logout").logoutSuccessUrl("/index");
		}
		 @Bean
		 BCryptPasswordEncoder getBCPE() {
			 return new BCryptPasswordEncoder();
		 }
		 
		 @Bean
		 public DaoAuthenticationProvider authenticationProvider() {
			 DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
			 
		        auth.setUserDetailsService(userService);
		        auth.setPasswordEncoder(getBCPE());
		 
			 return auth;
		 }

}
