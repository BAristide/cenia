package net.linksguard.ims.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 

 

/**
 
 * purpose: This class defines the category object that is used to categorize incident tickets.
 */
@Entity
public class ImsCategory {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    
    private String categoryName;

  
    private String categoryType;

    // constructor
    public ImsCategory() {
    }

    // follows are the accessor and modifier methods

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return this.categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

}