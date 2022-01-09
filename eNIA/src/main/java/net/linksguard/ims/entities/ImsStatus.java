package net.linksguard.ims.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImsStatus {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   // @NotNull
    private String statusName;

    // constructor
    public ImsStatus() {
    }

    // follows are the accessor and modifier methods

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return this.statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

}