package net.linksguard.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_FICHE",discriminatorType = DiscriminatorType.STRING,length = 2)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY,property="type")
@JsonSubTypes({
	@Type(name="FP",value=FicheProbleme.class),
	@Type(name="FI",value=FicheIncident.class)
	
	
})
public abstract class  Fiche implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private String titre;
	@Lob
	private String description;
	
	@Lob
	private String cause;
	

	private Date dateDebut;
	private Date dateFin; 
	
	

	private long serviceID;
	
	@ManyToOne
	@JoinColumn(name="idCreator")
	private User user;
	
	 
	@ManyToOne
	@JoinColumn(name="statusFiche")
	private StatutFiche statusFicheId;
	

	

	

	@OneToMany(mappedBy = "avcFichId")
	private Collection<EtaAvcMent> etaAvcMent;
	

	
	public Collection<EtaAvcMent> getEtaAvcMent() {
		return etaAvcMent;
	}

	public void setEtaAvcMent(Collection<EtaAvcMent> etaAvcMent) {
		this.etaAvcMent = etaAvcMent;
	}

	@ManyToMany
	@JoinTable(name = "equipSupp")
	private Collection<Equipes> equipes;
	
	@ManyToMany
	@JoinTable(name="servImpact")
	private Collection<ServicesPlateformes> servicesPlateformes;
	
	private Boolean sendNotification;
	 
	private Date creationDate;
	public Fiche() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	public Fiche(String titre, String description,   Collection<Equipes> equipes) {
		super();
		this.titre = titre;
		this.description = description;
	 
		this.equipes = equipes; 
	}

	public String getCause() {
		return cause;
	}
	
	@JsonIgnore
	public StatutFiche getStatusFicheId() {
		return statusFicheId;
	}
	@JsonSetter
	public void setStatusFicheId(StatutFiche statusFicheId) {
		this.statusFicheId = statusFicheId;
	}

	 

	 
	public void setCause(String cause) {
		this.cause = cause;
	}
	public Long getId() {
		return id;
	}
	public long getServiceID() {
		return serviceID;
	}
	 
	public void setServiceID(long serviceID) {
		this.serviceID = serviceID;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	 
	
	@JsonIgnore
	public User getUser() {
		return user;
	}
	@JsonSetter
	public void setUser(User user) {
		this.user = user;
	}


	 
	@JsonIgnore
	public Collection<Equipes> getEquipes() {
		return equipes;
	}
@JsonSetter
	public void setEquipes(Collection<Equipes> equipes) {
		this.equipes = equipes;
	}

	public Collection<ServicesPlateformes> getServicesPlateformes() {
		return servicesPlateformes;
	}
@JsonSetter
	public void setServicesPlateformes(Collection<ServicesPlateformes> servicesPlateformes) {
		this.servicesPlateformes = servicesPlateformes;
	}

	public Boolean getSendNotification() {
		return sendNotification;
	}

	public void setSendNotification(Boolean sendNotification) {
		this.sendNotification = sendNotification;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
