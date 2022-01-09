package net.linksguard.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.RandomStringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import net.linksguard.ims.entities.ImsAssignedGroup;
@Entity
@Table(name =  "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements Serializable {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String lastName;
	private String firstName;
    @NotNull
    @Column(nullable = false, unique = true)
	@Size(min = 1, message = "username is required")
	private String username;
	private String matricule;
	 @NotNull
	 @Column(nullable = false, unique = true)
	 @Size(min = 1, message = "Valid email is required")
	private String email;
	@Size(min=1, message = "Field cannot be empty")
	private String password;
	
	   private String phone;

	    // only used when user is changing their password, not stored in the database, this is the new password
	    // only used when user is changing their password, not stored in the database, this is the new password
	    @Transient
	    @Size(min=8, message = "Password must be at least 8 characters")
	    private String newPassword;

	    // only used when user is changing their password, not stored in the database, this the to verify the new password
	    @Transient
	    @Size(min=8, message = "Password must be at least 8 characters")
	    private String verifyPassword;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(
		            name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(
				            name = "role_id", referencedColumnName = "id"))	
	private Set<Roles> roles;
	
	 
	
	
	private String lieuHabitation;	
	private String contact1;
	private String contact2;
    private String token;

    // used to store the expiration time of the token
    private Timestamp tokenExpiration;
    private int active;
    // used to track number of times the toke was used, max of 3 attempts for any given token to complete the action
    // (either completing account set up or resetting the password)
    private int tokenAttempts;
	
	public String getUsername() {
		return username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getVerifyPassword() {
		return verifyPassword;
	}

	public void setVerifyPassword(String verifyPassword) {
		this.verifyPassword = verifyPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	// LAZY Signifique les donné seron chargé en cas de besoin 
	/*
	 * 
	 * Gestion Comptes Spring Boot JPA , 2015, 19 min
	 * 
	 * * OneToMany: 1 personne peut Creer Plusieurs Fiche
	 */
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<Fiche> fiche;
	
	private String comptefbc;
	private String comptelinkd;
	private Integer acces;
	private String comptetwter; 
	@ManyToOne
    private ImsAssignedGroup groupId;
 
	public Integer getAcces() {
		return acces;
	}

public User() {
	
}


	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	 public Set<Roles> getRoles() {
	        return this.roles;
	    }

	    public void setRoles(Set<Roles> roles) {
	        this.roles = roles;
	    }




	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Timestamp getTokenExpiration() {
		return tokenExpiration;
	}

	public void setTokenExpiration(Timestamp tokenExpiration) {
		this.tokenExpiration = tokenExpiration;
	}

	public int getTokenAttempts() {
		return tokenAttempts;
	}

	public void setTokenAttempts(int tokenAttempts) {
		this.tokenAttempts = tokenAttempts;
	}

	 



	public void setAcces(Integer acces) {
		this.acces = acces;
	}
	private String fonction;
	@ManyToMany
	@JoinTable(name = "PersEquipe")
 private Collection<Equipes> equipes;
	
	@ManyToMany
	@JoinTable(name = "notifSubscrip")
	private Collection<ServicesPlateformes> serPlateNotif;
	
 

	private String comment;	 
	 
	private Date creationDate;
	 
	
 

	 
	
	public User(String lastName, String firstName, String matricule,
			@NotNull @Size(min = 1, message = "Valid email is required") String email,
			@Size(min = 1, message = "Field cannot be empty") String password, Set<Roles> roles, Date creationDate) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.matricule = matricule;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.creationDate = creationDate;
	}

	public User(User user) {
		this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.groupId = user.getGroupId();
         
        this.active = user.getActive();
        this.roles = user.getRoles();
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLieuHabitation() {
		return lieuHabitation;
	}
	public void setLieuHabitation(String lieuHabitation) {
		this.lieuHabitation = lieuHabitation;
	}
	public String getContact1() {
		return contact1;
	}
	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}
	public String getContact2() {
		return contact2;
	}
	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}
	/*
	 * 
	 * ce pour ne que lorsque vous
	 * voulez consulter 1 employer, tout la liste des groupe soit envyer . cela creer de la lourdeur
	
	@JsonIgnore
	public Personne getEmployeeSupe() {
		return employeeSupe;
	}
 
	 Losque JsonIgnore est mise en plce, les get des info sero ignoreer mais aussi le set
	  
	@JsonSetter
	public void setEmployeeSupe(Personne employeeSupe) {
		this.employeeSupe = employeeSupe;
	}
	 */
	public Collection<Fiche> getFiche() {
		return fiche;
	}
	public void setFiche(Collection<Fiche> fiche) {
		this.fiche = fiche;
	}
	public String getComptefbc() {
		return comptefbc;
	}
	public void setComptefbc(String comptefbc) {
		this.comptefbc = comptefbc;
	}
	public String getComptelinkd() {
		return comptelinkd;
	}
	public void setComptelinkd(String comptelinkd) {
		this.comptelinkd = comptelinkd;
	}
	public String getComptetwter() {
		return comptetwter;
	}
	public void setComptetwter(String comptetwter) {
		this.comptetwter = comptetwter;
	}
	public String getFonction() {
		return fonction;
	}
	public void setFonction(String fonction) {
		this.fonction = fonction;
	}
	public Collection<Equipes> getEquipes() {
		return equipes;
	}
	public void setEquipes(Collection<Equipes> equipes) {
		this.equipes = equipes;
	}
	
	public Collection<ServicesPlateformes> getSerPlateNotif() {
		return serPlateNotif;
	}
	@JsonSetter
	public void setSerPlateNotif(Collection<ServicesPlateformes> serPlateNotif) {
		this.serPlateNotif = serPlateNotif;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	 
	public ImsAssignedGroup getGroupId() {
		return groupId;
	}

	public void setGroupId(ImsAssignedGroup groupId) {
		this.groupId = groupId;
	}

	/**
     * This method takes an integer length and returns a random password using alpha-numeric characters
     * @param length length of the desired new password
     * @return String the new random password
     */
    public static String createRandomPassword(int length){
        // set the characters to use and create and return the new random password

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        return RandomStringUtils.random(length, characters);
    }
}
