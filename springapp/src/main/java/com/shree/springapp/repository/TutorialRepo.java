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


import com.shree.springapp.entities.Tutorial;

@Repository
public interface TutorialRepo extends JpaRepository<Tutorial,Integer>{

    
    @Modifying
    @Transactional

    
    
    //post new data
    @Query(value="insert into Tutorial (course_id,video_url,upload_date,duration,transcript,resource_link) values(?,?,?,?,?,?)",nativeQuery = true)
    void createTutorial(int course_id,String video_url,Date upload_date,String duration,String transcript,String resource_link);
    
    //get all data
    @Query("select a from Tutorial a")
    List<Tutorial> getAllTutorials();
    
    //get data by id
    @Query("select a from Tutorial a where a.id=?1")
    Optional<Tutorial> findById(int id);
    
    //get data by resource_link
    @Query("select a from Tutorial a where a.resource_link = ?1")
    Optional<Tutorial> findByResource_link(String resource_link);
    
    //update data by id
    @Query("update Tutorial a SET a.duration = ?2 WHERE a.id = ?1")
    int updateTutorial(int id,String duration);
    
    //delete data by id
    @Query("delete from Tutorial a WHERE a.id = ?1")
    void deleteTutorial(int id);

    //sorting
    @Query("SELECT c FROM Tutorial c")
    List<Tutorial> findAllSort(Sort sort);
    
    //pagination
    @Query("select a from Tutorial a")
    Page<Tutorial> findAllPage(Pageable page);
    
    //page & sort
    @Query("select a from Tutorial a order by a.course_id ASC")
    Page<Tutorial> findAllPageSort(PageRequest withSort);
    


}
