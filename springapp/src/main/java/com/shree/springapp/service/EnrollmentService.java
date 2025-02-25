package com.shree.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shree.springapp.entities.Enrollment;
import com.shree.springapp.repository.EnrollmentRepo;

@Service
public class EnrollmentService {
    @Autowired
    EnrollmentRepo enobj;

    //post new data
    public Enrollment createEnrollment(Enrollment a)
    {
        return enobj.save(a);
    }

    //get all data
    public List<Enrollment> getAllEnrollments()
    {
        return enobj.findAll();
    }

    //get data by id
    public Optional<Enrollment> getEnrollmentById(int id)
    {
        return enobj.findById(id);
    }

    //get data by payment_status
    public Optional<Enrollment> getEnrollmentByPayment_status(String payment_status)
    {
        return enobj.findByPayment_status(payment_status);
    }

    //update data by id
    public Enrollment updateEnrollment(int id,Enrollment newEnrollment)
    {
        return enobj.findById(id).map(existingEnrollment -> {
            existingEnrollment.setId(newEnrollment.getId());
            existingEnrollment.setPayment_status(newEnrollment.getPayment_status());
            return enobj.save(existingEnrollment);

        }).orElseThrow(() -> new RuntimeException("Enrollment not found with id"+id));
    }

    //delete data by id
    public void deleteEnrollment(int id)
    {
        if(enobj.existsById(id))
        {
            enobj.deleteById(id);
        }
        else{
            throw new RuntimeException("Enrollment not found with id"+id);
        }
    }

    //sorting
    public List<Enrollment> EnrollmentSorted(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return enobj.findAllSort(sort);
    }

    //pagination
    public Page<Enrollment> EnrollmentPagination(int pagesize,int pagenumber)
    {
        Pageable page=PageRequest.of(pagenumber, pagesize);
        return enobj.findAllPage(page);
    }

    //page & sort
    public Page<Enrollment> EnrollmentPageSort(int pageSize,int pageNumber,String field)
    {
        return enobj.findAllPageSort(PageRequest.of(pageNumber, pageSize).
        withSort(Sort.by(Sort.Direction.ASC,field)));
    }

}
