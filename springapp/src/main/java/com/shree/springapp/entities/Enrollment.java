package com.shree.springapp.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date enrollment_date;
    private String payment_status;
    private String access_status;
    private String progress;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   
   
    public Date getEnrollment_date() {
        return enrollment_date;
    }
    public void setEnrollment_date(Date enrollment_date) {
        this.enrollment_date = enrollment_date;
    }
    public String getPayment_status() {
        return payment_status;
    }
    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }
    public String getAccess_status() {
        return access_status;
    }
    public void setAccess_status(String access_status) {
        this.access_status = access_status;
    }
    public String getProgress() {
        return progress;
    }
    public void setProgress(String progress) {
        this.progress = progress;
    }
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    public Enrollment(int id, Date enrollment_date, String payment_status, String access_status, String progress, User user, Course course) {
        this.id = id;
        this.enrollment_date = enrollment_date;
        this.payment_status = payment_status;
        this.access_status = access_status;
        this.progress = progress;
        this.user = user;
        this.course = course;
    }
    public Enrollment() {
    }
    




}
