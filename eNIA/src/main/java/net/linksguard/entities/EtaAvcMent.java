package net.linksguard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class EtaAvcMent  implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Lob
	private String libelle;
	@ManyToOne
	 @JoinColumn(name = "fichAvc")
	private Fiche avcFichId;
	
	
	private Date creationDate;


	public EtaAvcMent() {
		super();
		// TODO Auto-generated constructor stub
	}


	public EtaAvcMent(String libelle, Fiche avcFichId, Date creationDate) {
		super();
		this.libelle = libelle;
		this.avcFichId = avcFichId;
		this.creationDate = creationDate;
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


	public Fiche getAvcFichId() {
		return avcFichId;
	}

@JsonIgnore
	public void setAvcFichId(Fiche avcFichId) {
		this.avcFichId = avcFichId;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
