package net.linksguard.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@DiscriminatorValue("FI")
public class FicheIncident extends Fiche  {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ticketReference;
	public FicheIncident() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FicheIncident(String ticketReference) {
		super();
		this.ticketReference = ticketReference;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTicketReference() {
		return ticketReference;
	}
	public void setTicketReference(String ticketReference) {
		this.ticketReference = ticketReference;
	} 
 
}
