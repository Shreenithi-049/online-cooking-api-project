package com.shree.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shree.springapp.entities.User;
import com.shree.springapp.service.UserService;

@RestController
public class UserController {
    @Autowired
    UserService obj;
    @PostMapping("/api/users")
    public ResponseEntity<User> addUser(@RequestBody User a)
    {
        return new ResponseEntity<>(obj.createUser(a),HttpStatus.CREATED);
    }
    @GetMapping("/api/users/all")
    public ResponseEntity<List<User>>getAllUsers()
    {
        return new ResponseEntity<>(obj.getAllUsers(),HttpStatus.OK);
    }
    @PutMapping("/api/users/{id}")
    public ResponseEntity<User>updateUser(@PathVariable int id,@RequestBody User newUser)
    {
        try{
            return new ResponseEntity<>(obj.updateUser(id,newUser),HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void>deleteUser(@PathVariable int id)
    {
        try{
            obj.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<User>getUserById(@PathVariable int id)
    {
        return obj.getUserById(id).map(User -> new ResponseEntity<>(User,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getsortedusers/{field}")
    public List<User> getSortedUsers(@PathVariable String field)
    {
        return obj.UserSorted(field);
    }
    @GetMapping("/user/get/{pagenumber}/{pagesize}")
    public ResponseEntity<List<User>>GetPageUser(@PathVariable int pagenumber,@PathVariable int pagesize)
    {
        return new ResponseEntity<>(obj.UserPagination(pagenumber,pagesize),HttpStatus.OK);
    }

}
