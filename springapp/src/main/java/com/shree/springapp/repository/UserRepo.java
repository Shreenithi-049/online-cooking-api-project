package com.shree.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shree.springapp.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
    @Query("select a from User a")
    List<User> getAllUsers();

}
