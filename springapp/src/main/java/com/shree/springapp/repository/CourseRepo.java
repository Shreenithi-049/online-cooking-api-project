package com.shree.springapp.repository;

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

import com.shree.springapp.entities.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer>{
    @Modifying
    @Transactional
    
    //post new data
    @Query(value="insert into Course (title,description,instructor_id,difficulty_level,category,price,is_live,schedule,duration,rating,total_enrolled,status) values(?,?,?,?,?,?,?,?,?,?,?,?)",nativeQuery = true)
    void createCourse(String title,String description,int instructor_id,String difficulty_level,String category,double price,Boolean is_live,String schedule,String duration,String rating,int total_enrolled,String status);
    
    //get all data
    @Query("select a from Course a")
    List<Course> getAllCourses();
    
    //get data by id
    @Query("select a from Course a where a.id=?1")
    Optional<Course> findById(int id);
    
    //get data by category
    @Query("select a from Course a where a.category = ?1")
    Optional<Course> findByCategory(String category);
    
    //update data by id
    @Query("update Course a SET a.price = ?2 WHERE a.id = ?1")
    int updateCourse(int id, Double price);
    
    //delete data by id
    @Query("delete from Course a WHERE a.id = ?1")
    void deleteCourse(int id);

    //sorting
    @Query("SELECT c FROM Course c")
    List<Course> findAllSort(Sort sort);
    
    //pagination
    @Query("select a from Course a")
    Page<Course> findAllPage(Pageable page);
    
    //page & sort
    @Query("select a from Course a order by a.title ASC")
    Page<Course> findAllPageSort(PageRequest withSort);
    
   

    


}
