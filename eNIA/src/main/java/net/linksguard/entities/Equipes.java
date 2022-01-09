package net.linksguard.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany; 
@Entity
public class Equipes implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
 
	private Date creationDate;
	@ManyToMany(mappedBy = "equipes")
	private Collection<User> user;
	@ManyToMany(mappedBy = "equipes")
	private Collection<Fiche> fiche;
	public Equipes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Equipes(String nom, Date creationDate) {
		super();
		this.nom = nom;
		this.creationDate = creationDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	 
	
	public Collection<User> getUser() {
		return user;
	}
	public void setUser(Collection<User> user) {
		this.user = user;
	}
 
	public Collection<Fiche> getFiche() {
		return fiche;
	}
	public void setFiche(Collection<Fiche> fiche) {
		this.fiche = fiche;
	} 
	

}
