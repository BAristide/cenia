package net.linksguard.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob; 
@Entity
public class KBcase implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date startTime;
	private Date creationTime;
	private Long createdBy;
	private String projectLabel;
	private String processionPriority;
	private String shortLabel;
	@Lob
	private String descriptionLabel;
	
	private String causeLabel;
	private String fileUploadPath;
	private String shortCauseLabel;
	private Date restaurationDate;
	private Date closingDate;
	
	private Long restaurationUser;

	public KBcase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KBcase(Date startTime, Date creationTime, Long createdBy, String projectLabel, String processionPriority,
			String shortLabel, String descriptionLabel, String causeLabel, String fileUploadPath,
			String shortCauseLabel, Date restaurationDate, Date closingDate, Long restaurationUser) {
		super();
		this.startTime = startTime;
		this.creationTime = creationTime;
		this.createdBy = createdBy;
		this.projectLabel = projectLabel;
		this.processionPriority = processionPriority;
		this.shortLabel = shortLabel;
		this.descriptionLabel = descriptionLabel;
		this.causeLabel = causeLabel;
		this.fileUploadPath = fileUploadPath;
		this.shortCauseLabel = shortCauseLabel;
		this.restaurationDate = restaurationDate;
		this.closingDate = closingDate;
		this.restaurationUser = restaurationUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getProjectLabel() {
		return projectLabel;
	}

	public void setProjectLabel(String projectLabel) {
		this.projectLabel = projectLabel;
	}

	public String getProcessionPriority() {
		return processionPriority;
	}

	public void setProcessionPriority(String processionPriority) {
		this.processionPriority = processionPriority;
	}

	public String getShortLabel() {
		return shortLabel;
	}

	public void setShortLabel(String shortLabel) {
		this.shortLabel = shortLabel;
	}

	public String getDescriptionLabel() {
		return descriptionLabel;
	}

	public void setDescriptionLabel(String descriptionLabel) {
		this.descriptionLabel = descriptionLabel;
	}

	public String getCauseLabel() {
		return causeLabel;
	}

	public void setCauseLabel(String causeLabel) {
		this.causeLabel = causeLabel;
	}

	public String getFileUploadPath() {
		return fileUploadPath;
	}

	public void setFileUploadPath(String fileUploadPath) {
		this.fileUploadPath = fileUploadPath;
	}

	public String getShortCauseLabel() {
		return shortCauseLabel;
	}

	public void setShortCauseLabel(String shortCauseLabel) {
		this.shortCauseLabel = shortCauseLabel;
	}

	public Date getRestaurationDate() {
		return restaurationDate;
	}

	public void setRestaurationDate(Date restaurationDate) {
		this.restaurationDate = restaurationDate;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public Long getRestaurationUser() {
		return restaurationUser;
	}

	public void setRestaurationUser(Long restaurationUser) {
		this.restaurationUser = restaurationUser;
	}
 
	 
	
	
	

}
