
package com.shree.springapp.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shree.springapp.entities.Enrollment;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Integer> {

  @Modifying
  @Transactional

    
    
    // Fetch all enrollments
    @Query("SELECT e FROM Enrollment e")
    List<Enrollment> getAllEnrollments();

    // Fetch enrollment by ID (not needed since JpaRepository provides findById)
    @Query("SELECT e FROM Enrollment e WHERE e.id = ?1")
    Optional<Enrollment> findById(int id);
    
    // Fetch enrollments by payment status
    @Query("SELECT e FROM Enrollment e WHERE e.payment_status = ?1")
    Optional<Enrollment> findByPaymentStatus(String payment_status);
    
    // Update progress by enrollment ID
    @Modifying
    @Transactional
    @Query("UPDATE Enrollment e SET e.progress = ?2 WHERE e.id = ?1")
    int updateEnrollment(int id, String progress);
    
    // Delete enrollment by ID (Use deleteById instead)
    @Modifying
    @Transactional
    void deleteById(int id);

    
}
