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

import com.shree.springapp.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
    @Modifying
    @Transactional
    
    //post new data
    @Query(value="insert into User (name,email,password,phone,profilePicture,cookingInterest,role,regisDate,subpStatus,paymethod) values(?,?,?,?,?,?,?,?,?,?)",nativeQuery = true)
    void createUser(String name,String email,String password,int phone,String profilePicture,String cookingInterest,String role,Date regisDate,String subpStatus,String paymethod);
    
    //get all data
    @Query("select a from User a")
    List<User> getAllUsers();
    
    //get data by id
    @Query("select a from User a where a.id=?1")
    Optional<User> findById(int id);
    
    //get data by category
    @Query("select a from User a where a.phone=?1")
    Optional<User> findByPhone(int phone);
    
    //update data by id
    @Query("update User a SET a.email = ?2 WHERE a.id = ?1")
    int updateUser(int id, String email);
    
    //delete data by id
    @Query("delete from User a WHERE a.id = ?1")
    void deleteUser(int id);

    //sorting
    @Query("SELECT c FROM User c")
    List<User> findAllSort(Sort sort);
    
    //pagination
    @Query("select a from User a")
    Page<User> findAllPage(Pageable page);
    
    //page & sort
    @Query("select a from User a order by a.name ASC")
    Page<User> findAllPageSort(PageRequest withSort);
    
}
