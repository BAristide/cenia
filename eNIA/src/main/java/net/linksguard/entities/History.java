package net.linksguard.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class History {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private String actionDone;
	private Date actionDate;
	private Date creationDate;
}
