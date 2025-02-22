package com.shree.springapp.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
    private String password;
    private int phone;
    private String profilePicture;
    private String cookingInterest;
    private String role;
    private Date regisDate;
    private String subpStatus;
    private String paymethod;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getProfilePicture() {
        return profilePicture;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public String getCookingInterest() {
        return cookingInterest;
    }
    public void setCookingInterest(String cookingInterest) {
        this.cookingInterest = cookingInterest;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public Date getRegisDate() {
        return regisDate;
    }
    public void setRegisDate(Date regisDate) {
        this.regisDate = regisDate;
    }
    public String getSubpStatus() {
        return subpStatus;
    }
    public void setSubpStatus(String subpStatus) {
        this.subpStatus = subpStatus;
    }
    public String getPaymethod() {
        return paymethod;
    }
    public void setPaymethod(String paymethod) {
        paymethod = paymethod;
    }
    public User(int id,String name,String email,String password,int phone,String profilePicture,String cookingInterest,String role,Date regisDate,String subpStatus,String paymethod)
    {
        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
        this.phone=phone;
        this.profilePicture=profilePicture;
        this.cookingInterest=cookingInterest;
        this.role=role;
        this.regisDate=regisDate;
        this.subpStatus=subpStatus;
        this.paymethod=paymethod;
    }
    public User() {
    }


}
