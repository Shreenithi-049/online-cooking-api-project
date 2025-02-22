package com.shree.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public User createUser(User a)
    {
        return obj.save(a);
    }
    public List<User> getAllUsers()
    {
        return obj.findAll();
    }
    public Optional<User> getUserById(int id)
    {
        return obj.findById(id);
    }
    public User updateUser(int id,User newUser)
    {
        return obj.findById(id).map(existingUser -> {
            existingUser.setId(newUser.getId());
            existingUser.setName(newUser.getName());
            return obj.save(existingUser);

        }).orElseThrow(() -> new RuntimeException("User not found with id"+id));
    }
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
    public List<User> UserSorted(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return obj.findAll(sort);
    }
    public List<User> UserPagination(int pagesize,int pagenumber)
    {
        Pageable page=PageRequest.of(pagenumber, pagesize);
        return obj.findAll(page).getContent();
    }
    

}
