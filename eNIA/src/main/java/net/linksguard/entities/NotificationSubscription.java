package net.linksguard.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class NotificationSubscription {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long serviceID;
	private Long userID; 
	private String nameService; 
	private Date creationDate;
	public NotificationSubscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public NotificationSubscription(Long serviceID, Long userID, String nameService) {
		super();
		this.serviceID = serviceID;
		this.userID = userID;
		this.nameService = nameService;
	}

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getServiceID() {
		return serviceID;
	}
	public void setServiceID(Long serviceID) {
		this.serviceID = serviceID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
