package net.linksguard.ims.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 
 * last updated date: August 25, 2018
 * purpose: This class defines the object to assign a severity to an incident ticket.
 */
@Entity
public class ImsSeverity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	// @NotNull
    private String severityName;

    @OneToMany
    @JoinColumn(name = "severity_id")
    private List<ImsTicket> severityTickets = new ArrayList<>();

    // constructor
    public ImsSeverity() {
    }

    // follows are the accessor and modifier methods

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeverityName() {
        return this.severityName;
    }

    public void setSeverityName(String severityName) {
        this.severityName = severityName;
    }

    public List<ImsTicket> getTickets() {
        return this.severityTickets;
    }

    public void setTickets(List<ImsTicket> tickets) {
        this.severityTickets = tickets;
    }
}
