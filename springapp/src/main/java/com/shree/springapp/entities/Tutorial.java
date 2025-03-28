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

public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int course_id;
    private String video_url;
    private Date upload_date;
    private String duration;
    private String transcript;
    private String resource_link;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

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
    public int getCourse_id() {
        return course_id;
    }
    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
    public String getVideo_url() {
        return video_url;
    }
    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
    public Date getUpload_date() {
        return upload_date;
    }
    public void setUpload_date(Date upload_date) {
        this.upload_date = upload_date;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getTranscript() {
        return transcript;
    }
    public void setTranscript(String transcript) {
        this.transcript = transcript;
    }
    public String getResource_link() {
        return resource_link;
    }
    public void setResource_link(String resource_link) {
        this.resource_link = resource_link;
    } 
    public Tutorial(int id,int course_id,String video_url,Date upload_date,String duration,String transcript,String resource_link,User user)
    {
        this.id=id;
        this.course_id=course_id;
        this.video_url=video_url;
        this.upload_date=upload_date;
        this.duration=duration;
        this.transcript=transcript;
        this.resource_link=resource_link;
        this.user=user;
    }
    public Tutorial() {
    }
    



}
