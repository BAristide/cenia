package net.linksguard.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class StatutFiche {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	private String libelle;
 @OneToMany(mappedBy = "statusFicheId")
	private Collection<Fiche> fiches;
public StatutFiche(String libelle) {
	super();
	this.libelle = libelle;
}

public StatutFiche() {
	super();
	// TODO Auto-generated constructor stub
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getLibelle() {
	return libelle;
}
public void setLibelle(String libelle) {
	this.libelle = libelle;
}
public Collection<Fiche> getFiches() {
	return fiches;
}
public void setFiches(Collection<Fiche> fiches) {
	this.fiches = fiches;
}
 
 
 
}
