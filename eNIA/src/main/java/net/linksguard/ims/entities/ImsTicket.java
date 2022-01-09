package net.linksguard.ims.entities;


 

import net.linksguard.entities.User;

import java.io.Serializable; 
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

 

 

@Entity
public class ImsTicket implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     
    private Date createTimestamp;
    
    private Date updateTimestamp;
    @ManyToOne
    private ImsCategory categoryMain;
    @ManyToOne
    private ImsCategory categorySub;
    @ManyToOne
    private ImsCategory categoryDetail;
 
    @Size(min=1, message = "Field cannot be empty")
    private String title;
    @NotNull
    @Column(length = 500, columnDefinition = "Text")
    @Size(min=1, max=500, message = "Description must be between 1 and 500 characters")
    @Lob
    private String description;
    @ManyToOne
    private ImsAssignedGroup assignedGroup;
    @ManyToOne
    private User assignedPerson;
    @ManyToOne
    private ImsSeverity severity;
    @ManyToOne
    private ImsStatus status;
    @NotNull
    @Size(min=1, message = "Field cannot be empty")
    private String requestorName;
    @NotNull
    @Size(min=1, message = "Field cannot be empty")
    private String requestorPhone;
    @NotNull
    @Size(min=1, message = "Field cannot be empty")
    private String requestorEmail;
    @NotNull
    @Size(min=1, message = "Field cannot be empty")
    private String location;
     
    @Size(min=1, message = "Field cannot be empty")
    private String locationDetail;
    @Column(length = 65535, columnDefinition = "Text")
    private StringBuilder log;
    @Transient
    private String update;

    public ImsTicket(Date createTimestamp, Date updateTimestamp, ImsCategory categoryMain, ImsCategory categorySub, ImsCategory categoryDetail, String title,
                  String description, ImsAssignedGroup assignedGroup, User assignedPerson, ImsSeverity severity, ImsStatus status,
                  String requestorName, String requestorPhone, String requestorEmail, String location,
                  String locationDetail, StringBuilder log) {

        this.categoryMain = categoryMain;
        this.categorySub = categorySub;
        this.categoryDetail = categoryDetail;
        this.title = title;
        this.description = description;
        this.assignedGroup = assignedGroup;
        this.assignedPerson = assignedPerson;
        this.severity = severity;
        this.status = status;
        this.requestorName = requestorName;
        this.requestorPhone = requestorPhone;
        this.requestorEmail = requestorEmail;
        this.location = location;
        this.locationDetail = locationDetail;
        this.createTimestamp = createTimestamp;
        this.updateTimestamp = updateTimestamp;
        this.log = log;
    }

    public ImsTicket() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     

     

    public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public ImsCategory getCategoryMain() {
        return categoryMain;
    }

    public void setCategoryMain(ImsCategory categoryMain) {
        this.categoryMain = categoryMain;
    }

    public ImsCategory getCategorySub() {
        return categorySub;
    }

    public void setCategorySub(ImsCategory categorySub) {
        this.categorySub = categorySub;
    }

    public ImsCategory getCategoryDetail() {
        return categoryDetail;
    }

    public void setCategoryDetail(ImsCategory categoryDetail) {
        this.categoryDetail = categoryDetail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImsAssignedGroup getAssignedGroup() {
        return assignedGroup;
    }

    public void setAssignedGroup(ImsAssignedGroup assignedGroup) {
        this.assignedGroup = assignedGroup;
    }

    public User getAssignedPerson() {
        return assignedPerson;
    }

    public void setAssignedPerson(User assignedPerson) {
        this.assignedPerson = assignedPerson;
    }

    public ImsSeverity getSeverity() {
        return severity;
    }

    public void setSeverity(ImsSeverity severity) {
        this.severity = severity;
    }

    public String getRequestorName() {
        return requestorName;
    }

    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

    public String getRequestorPhone() {
        return requestorPhone;
    }

    public void setRequestorPhone(String requestorPhone) {
        this.requestorPhone = requestorPhone;
    }

    public String getRequestorEmail() {
        return requestorEmail;
    }

    public void setRequestorEmail(String requestorEmail) {
        this.requestorEmail = requestorEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public void setAssignedGroup(Optional<ImsAssignedGroup> group) {
        this.assignedGroup = assignedGroup;
    }

    public void setSeverity(Optional<ImsSeverity> displaySeverity) {
    }

    public void setCategoryMain(Optional<ImsCategory> category) {
    }

    public ImsStatus getStatus() {
        return status;
    }

    public void setStatus(ImsStatus status) {
        this.status = status;
    }

    public void setStatus(Optional<ImsStatus> status) {

    }

    public StringBuilder getLog() {
        return log;
    }

    public void setLog(StringBuilder log) {
        this.log = log;
    }

    public void setCategorySub(Optional<ImsCategory> subCategory) {
    }

    public void setCategoryDetail(Optional<ImsCategory> detailCategory) {
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public void setAssignedPerson(Optional<User> user) {

    }

    public void setStatus(int statusId) {

    }
}
