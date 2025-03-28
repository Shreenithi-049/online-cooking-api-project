package com.shree.springapp.entities;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private int instructor_id;
    private String difficulty_level;
    private String category;
    private double price;
    private Boolean is_live;
    private String schedule;
    private String duration;
    private String rating;
    private int total_enrolled;
    private String status;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Enrollment> enrollments;

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getInstructor_id() {
        return instructor_id;
    }
    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }
    public String getDifficulty_level() {
        return difficulty_level;
    }
    public void setDifficulty_level(String difficulty_level) {
        this.difficulty_level = difficulty_level;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Boolean getIs_live() {
        return is_live;
    }
    public void setIs_live(Boolean is_live) {
        this.is_live = is_live;
    }
    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    public int getTotal_enrolled() {
        return total_enrolled;
    }
    public void setTotal_enrolled(int total_enrolled) {
        this.total_enrolled = total_enrolled;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Course(int id, String title, String description, int instructor_id, String difficulty_level, String category, double price, Boolean is_live, String schedule, String duration, String rating, int total_enrolled, String status,List<Enrollment> enrollments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructor_id = instructor_id;
        this.difficulty_level = difficulty_level;
        this.category = category;
        this.price = price;
        this.is_live = is_live;
        this.schedule = schedule;
        this.duration = duration;
        this.rating = rating;
        this.total_enrolled = total_enrolled;
        this.status = status;
        this.enrollments=enrollments;
    }
    public Course() {
    }
    
    

}
