package com.shree.springapp.controller;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;




// import com.fasterxml.jackson.annotation.JsonIgnore;
// import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class message {
    // @JsonIgnore
    @Id
    private int id;
    // @JsonProperty("fname")
    private String firstname;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    private String lastname;
    public message(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public message() {
    }
    
    
    
}
