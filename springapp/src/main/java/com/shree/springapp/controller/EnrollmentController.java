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


import com.shree.springapp.entities.Enrollment;
import com.shree.springapp.service.EnrollmentService;

@RestController
public class EnrollmentController {
    @Autowired
    EnrollmentService enobj;

    //Posting new data
    @PostMapping("/api/enrollments")
    public ResponseEntity<Enrollment> addEnrollment(@RequestBody Enrollment a)
    {
        return new ResponseEntity<>(enobj.createEnrollment(a),HttpStatus.CREATED);
    }

    //Getting all tha data
    @GetMapping("/api/enrollments/all")
    public ResponseEntity<List<Enrollment>>getAllEnrollments()
    {
        return new ResponseEntity<>(enobj.getAllEnrollments(),HttpStatus.OK);
    }

    //Updating the data by id
    @PutMapping("/api/enrollments/{id}")
    public ResponseEntity<Enrollment>updateEnrollment(@PathVariable int id,@RequestBody Enrollment neweEnrollment)
    {
        try{
            return new ResponseEntity<>(enobj.updateEnrollment(id,neweEnrollment),HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting the data by id
    @DeleteMapping("/api/enrollments/{id}")
    public ResponseEntity<Void>deleteEnrollment(@PathVariable int id)
    {
        try{
            enobj.deleteEnrollment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //Getting the data by id
    @GetMapping("/api/enrollments/{id}")
    public ResponseEntity<Enrollment>getEnrollmentById(@PathVariable int id)
    {
        return enobj.getEnrollmentById(id).map(Enrollment -> new ResponseEntity<>(Enrollment,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Getting the data by payment_status
    @GetMapping("/api/enrollments/{payments_status}")
    public ResponseEntity<Enrollment>getEnrollmentByPayment_status(@PathVariable String payment_status)
    {
        return enobj.getEnrollmentByPayment_status(payment_status).map(Enrollment -> new ResponseEntity<>(Enrollment,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Getting the data in sorted order
    @GetMapping("/getsortedenrollment/{field}")
    public List<Enrollment> getSortedEnrollments(@PathVariable String field)
    {
        return enobj.EnrollmentSorted(field);
    }

    //Getting the data by specifying page number and size
    @GetMapping("/enrollment/get/{pagenumber}/{pagesize}")
    public ResponseEntity<Page<Enrollment>>GetPageEnrollment(@PathVariable int pagenumber,@PathVariable int pagesize)
    {
        return new ResponseEntity<>(enobj.EnrollmentPagination(pagenumber,pagesize),HttpStatus.OK);
    }

    //Getting the data by implementing both paginationa and sorting
    @GetMapping("/api/enrollment/{pagenumber}/{pagesize}/{field}")
    public Page<Enrollment> pagesorting(@PathVariable int pagenumber,@PathVariable int pagesize,@PathVariable String field)
    {
        return enobj.EnrollmentPageSort(pagesize,pagenumber, field);
    }

}
