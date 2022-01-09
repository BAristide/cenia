package net.linksguard.ims.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ImsAssignedGroup {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

     //@NotNull
     @Size(min = 1, message = "Required field")
    private String groupName;

    // constructor
    public ImsAssignedGroup() { 
    	
    }

    // follows are the accessor and modifier methods

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}