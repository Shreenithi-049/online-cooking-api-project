package com.shree.springapp.entities;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private int course_id;
    private Date enrollment_date;
    private String payment_status;
    private String access_status;
    private String progress;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getCourse_id() {
        return course_id;
    }
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
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
    public Enrollment(int id,int user_id,int course_id,Date enrollment_date,String payment_status,String access_status,String progress)
    {
        this.id=id;
        this.user_id=user_id;
        this.course_id=course_id;
        this.enrollment_date=enrollment_date;
        this.payment_status=payment_status;
        this.access_status=access_status;
        this.progress=progress;
    }
    public Enrollment() {
    }




}
