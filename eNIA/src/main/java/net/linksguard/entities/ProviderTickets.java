package net.linksguard.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;

@Entity
public class ProviderTickets {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String reference;
	private String titre;
	private String serviceName;
	@Transient
	private String csvdateDebut;
	private Date dateDebut;
	@Transient
	private String csvDateFin;
	private Date dateFin;
	
	private String resolveTime;
	
	private String priority;
	
	
	public ProviderTickets() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getResolveTime() {
		return resolveTime;
	}
	public void setResolveTime(String resolveTime) {
		this.resolveTime = resolveTime;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getCsvdateDebut() {
		return csvdateDebut;
	}
	public void setCsvdateDebut(String csvdateDebut) {
		this.csvdateDebut = csvdateDebut;
	}
	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public String getCsvDateFin() {
		return csvDateFin;
	}
	public void setCsvDateFin(String csvDateFin) {
		this.csvDateFin = csvDateFin;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

}
