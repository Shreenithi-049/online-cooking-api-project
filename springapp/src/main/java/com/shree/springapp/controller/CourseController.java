package com.shree.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shree.springapp.entities.Course;
import com.shree.springapp.service.CourseService;

@RestController
public class CourseController {
    @Autowired
    CourseService courseobj;

    //Posting new data
    @PostMapping("/api/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course a)
    {
        return new ResponseEntity<>(courseobj.createCourse(a),HttpStatus.CREATED);
    }

    //Getting all tha data
    @GetMapping("/api/courses/all")
    public ResponseEntity<List<Course>>getAllCourses()
    {
        return new ResponseEntity<>(courseobj.getAllCourses(),HttpStatus.OK);
    }

    //Updating the data by id
    @PutMapping("/api/courses/{id}")
    public ResponseEntity<Course>updateCourse(@PathVariable int id,@RequestBody Course newCourse)
    {
        try{
            return new ResponseEntity<>(courseobj.updateCourse(id,newCourse),HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting the data by id
    @DeleteMapping("/api/courses/{id}")
    public ResponseEntity<Void>deleteCourse(@PathVariable int id)
    {
        try{
            courseobj.deleteCourse(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //Getting the data by id
    @GetMapping("/api/courses/{id}")
    public ResponseEntity<Course>getCourseById(@PathVariable int id)
    {
        return courseobj.getCourseById(id).map(Course -> new ResponseEntity<>(Course,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Getting the data by category
    @GetMapping("/api/courses/{category}")
    public ResponseEntity<Course>getCourseByCategory(@PathVariable String category)
    {
        return courseobj.getCourseByCategory(category).map(Course -> new ResponseEntity<>(Course,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Getting the data in sorted order
    @GetMapping("/getsortedcourses/{field}")
    public List<Course> getSortedCourses(@PathVariable String field)
    {
        return courseobj.CourseSorted(field);
    }

    //Getting the data by specifying page number and size
    @GetMapping("/course/get/{pagenumber}/{pagesize}")
    public ResponseEntity<Page<Course>>GetPageCourse(@PathVariable int pagenumber,@PathVariable int pagesize)
    {
        return new ResponseEntity<>(courseobj.CoursePagination(pagenumber,pagesize),HttpStatus.OK);
    }

    //Getting the data by implementing both paginationa and sorting
    @GetMapping("/api/course/{pagenumber}/{pagesize}/{field}")
    public Page<Course> pagesorting(@PathVariable int pagenumber,@PathVariable int pagesize,@PathVariable String field)
    {
        return courseobj.CoursePageSort(pagesize,pagenumber, field);
    }

}
