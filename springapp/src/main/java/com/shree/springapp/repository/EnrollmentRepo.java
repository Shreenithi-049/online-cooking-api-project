package com.shree.springapp.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.shree.springapp.entities.Enrollment;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment,Integer>{
    @Modifying
    @Transactional
    
    //post new data
    @Query(value="insert into Enrollment (user_id,course_id,enrollment_date,payment_status,access_status,progress) values(?,?,?,?,?,?)",nativeQuery = true)
    void createEnrollment(int user_id,int course_id,Date enrollment_date,String payment_status,String access_status,String progress);
    
    //get all data
    @Query("select a from Enrollment a")
    List<Enrollment> getAllEnrollments();
    
    //get data by id
    @Query("select a from Enrollment a where a.id=?1")
    Optional<Enrollment> findById(int id);
    
    //get data by payment_status
    @Query("select a from Enrollment a where a.payment_status = ?1")
    Optional<Enrollment> findByPayment_status(String payment_status);
    
    //update data by id
    @Query("update Enrollment a SET a.progress = ?2 WHERE a.id = ?1")
    int updateEnrollment(int id, Double price);
    
    //delete data by id
    @Query("delete from Enrollment a WHERE a.id = ?1")
    void deleteEnrollment(int id);

    //sorting
    @Query("SELECT c FROM Enrollment c")
    List<Enrollment> findAllSort(Sort sort);
    
    //pagination
    @Query("select a from Enrollment a")
    Page<Enrollment> findAllPage(Pageable page);
    
    //page & sort
    @Query("select a from Enrollment a order by a.user_id ASC")
    Page<Enrollment> findAllPageSort(PageRequest withSort);

}
