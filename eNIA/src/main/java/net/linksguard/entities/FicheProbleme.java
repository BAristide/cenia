package net.linksguard.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@DiscriminatorValue("FP")
public class FicheProbleme extends Fiche{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ticketReference;
	public FicheProbleme() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FicheProbleme(String titre, String description, Collection<Equipes> equipes, String ticketReference) {
		super(titre, description, equipes);
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
