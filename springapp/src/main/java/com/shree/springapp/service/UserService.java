package com.shree.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shree.springapp.entities.User;
import com.shree.springapp.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    UserRepo obj;

    //post new data
    public User createUser(User a)
    {
        return obj.save(a);
    }

    //get all data
    public List<User> getAllUsers()
    {
        return obj.findAll();
    }

    //get data by id
    public Optional<User> getUserById(int id)
    {
        return obj.findById(id);
    }

    //get data by phone
    public Optional<User> getUserByPhone(int phone)
    {
        return obj.findByPhone(phone);
    }

    //update data by id
    public User updateUser(int id,User newUser)
    {
        return obj.findById(id).map(existingUser -> {
            existingUser.setId(newUser.getId());
            existingUser.setName(newUser.getName());
            return obj.save(existingUser);

        }).orElseThrow(() -> new RuntimeException("User not found with id"+id));
    }

    //delete data by id
    public void deleteUser(int id)
    {
        if(obj.existsById(id))
        {
            obj.deleteById(id);
        }
        else{
            throw new RuntimeException("User not found with id"+id);
        }
    }

    //sorting
    public List<User> UserSorted(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return obj.findAllSort(sort);
    }

    //pagination
    public Page<User> UserPagination(int pagesize,int pagenumber)
    {
        Pageable page=PageRequest.of(pagenumber, pagesize);
        return obj.findAllPage(page);
    }

     //page & sort
     public Page<User> UserPageSort(int pageSize,int pageNumber,String field)
     {
         return obj.findAllPageSort(PageRequest.of(pageNumber, pageSize).
         withSort(Sort.by(Sort.Direction.ASC,field)));
     }
    

}
