package com.shree.springapp.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shree.springapp.entities.Enrollment;
import com.shree.springapp.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    EnrollmentService enobj;

    // POST: Add new enrollment
    @PostMapping
    public ResponseEntity<Enrollment> addEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enobj.createEnrollment(enrollment));
    }

    // GET: Retrieve all enrollments
    @GetMapping("/all")
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enobj.getAllEnrollments());
    }

    // GET: Retrieve enrollment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable int id) {
        return enobj.getEnrollmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET: Retrieve enrollment by payment status
    @GetMapping("/payment/{payment_status}")
    public ResponseEntity<Enrollment> getEnrollmentByPaymentStatus(@PathVariable String payment_status) {
        return enobj.getEnrollmentByPaymentStatus(payment_status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: Update enrollment by ID
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable int id, @RequestBody Enrollment newEnrollment) {
        try {
            return ResponseEntity.ok(enobj.updateEnrollment(id, newEnrollment));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // DELETE: Delete enrollment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable int id) {
        try {
            enobj.deleteEnrollment(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // GET: Retrieve sorted enrollments
    @GetMapping("/sort/{field}")
    public ResponseEntity<List<Enrollment>> getSortedEnrollments(@PathVariable String field) {
        return ResponseEntity.ok(enobj.EnrollmentSorted(field));
    }

    // GET: Retrieve paginated enrollments
    @GetMapping("/page/{pagenumber}/size/{pagesize}")
    public ResponseEntity<Page<Enrollment>> getPagedEnrollments(
            @PathVariable int pagenumber,
            @PathVariable int pagesize) {
        return ResponseEntity.ok(enobj.EnrollmentPagination(pagenumber, pagesize));
    }

    // GET: Retrieve enrollments with pagination and sorting
    @GetMapping("/page/{pagenumber}/size/{pagesize}/sort/{field}")
    public ResponseEntity<Page<Enrollment>> getPagedSortedEnrollments(
            @PathVariable int pagenumber,
            @PathVariable int pagesize,
            @PathVariable String field) {
        return ResponseEntity.ok(enobj.EnrollmentPageSort(pagenumber, pagesize, field));
    }
}
