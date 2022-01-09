package net.linksguard.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class ServicesPlateformes {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	@Column(columnDefinition = "varchar(255) default 'GOLD'")
	private String serviceClasse;;
	
	private Date creationDate;
	@ManyToMany(mappedBy = "servicesPlateformes")
	private Collection<Fiche> fiche;
	
	@ManyToMany(mappedBy = "serPlateNotif")
	private Collection<User> User;
	
	
	@JsonIgnore
	public Collection<User> getUser() {
		return User;
	}
	public void setUser(Collection<User> user) {
		User = user;
	}
	public ServicesPlateformes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ServicesPlateformes(String name, Date creationDate) {
		super();
		this.name = name;
		this.creationDate = creationDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	@JsonIgnore
	public Collection<Fiche> getFiche() {
		return fiche;
	}
	@JsonSetter
	public void setFiche(Collection<Fiche> fiche) {
		this.fiche = fiche;
	}
	public String getServiceClasse() {
		return serviceClasse;
	}
	public void setServiceClasse(String serviceClasse) {
		this.serviceClasse = serviceClasse;
	}
	 
	
	

}
