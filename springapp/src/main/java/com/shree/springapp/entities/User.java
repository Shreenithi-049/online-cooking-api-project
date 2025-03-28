package com.shree.springapp.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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

    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    @JsonManagedReference
    List<Tutorial> tutorial=new ArrayList<>();
    
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL)
    @JsonManagedReference
    List<Enrollment> enrollment=new ArrayList<>();

    public void addEnrollment(Enrollment enrollments) {
        enrollment.add(enrollments);
        enrollments.setUser(this);
    }

    public List<Enrollment> getEnrollment() {
        return enrollment;
    }
    public void setEnrollment(List<Enrollment> enrollment) {
        this.enrollment = enrollment;
    }
    public List<Tutorial> getTutorial() {
        return tutorial;
    }
    public void setTutorial(List<Tutorial> tutorial) {
        this.tutorial = tutorial;
    }
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
    public User(int id,String name,String email,String password,int phone,String profilePicture,String cookingInterest,String role,Date regisDate,String subpStatus,String paymethod,List<Tutorial> tutorial,List<Enrollment> enrollment)
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
        this.tutorial=tutorial;
        this.enrollment=enrollment;
    }
    public User() {
    }


}
