package com.shree.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shree.springapp.controller.message;
@Repository
public interface MessageRepo extends JpaRepository<message,Integer>{
    @Modifying
    @Query(value="insert into message (id,firstname,lastname) values(?,?,?)",nativeQuery = true)
    void postmessage(int id,String firstname,String lastlame);
    @Query("Select a from message a")
    List<message> getAllMessages();

    @Query("select a from message a where a.id=?1")
    List<message> getMessageById(int id);
   
    
    @Modifying
    @Transactional
    @Query("DELETE FROM message p WHERE p.id = ?1")
    int deleteProductById(Integer id);


}
