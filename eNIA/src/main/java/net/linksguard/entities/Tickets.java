package net.linksguard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.annotation.Transient;

@Entity
public class Tickets  implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String reference;
	private String titre;
	private String serviceName;
	 
	private Date dateDebut;
	private Date creationDate;
	private Date resolutionDate;
	private Date closeDate;
	
	private String resolveTime;
	
	private String priority;

}
