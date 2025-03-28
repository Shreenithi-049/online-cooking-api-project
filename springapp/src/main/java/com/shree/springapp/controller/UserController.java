package com.shree.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shree.springapp.entities.Course;
import com.shree.springapp.entities.User;
import com.shree.springapp.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService obj;

    //Posting new data
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User a)
    {
        return new ResponseEntity<>(obj.saveUser(a),HttpStatus.CREATED);
    }

    //Getting all tha data
    @GetMapping("/all")
    public ResponseEntity<List<User>>getAllUsers()
    {
        return new ResponseEntity<>(obj.getAllUsers(),HttpStatus.OK);
    }

    //Updating the data by id
    @PutMapping("/{id}")
    public ResponseEntity<User>updateUser(@PathVariable int id,@RequestBody User newUser)
    {
        try{
            return new ResponseEntity<>(obj.updateUser(id,newUser),HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting the data by id
    @DeleteMapping("/{id}")
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

    //Getting the data by id
    @GetMapping("/getuser/{id}")
    public ResponseEntity<User>getUserById(@PathVariable int id)
    {
        return obj.getUserById(id).map(User -> new ResponseEntity<>(User,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Getting the data by phone
    @GetMapping("/{phone}")
    public ResponseEntity<User>getUserByPhone(@PathVariable int phone)
    {
        return obj.getUserByPhone(phone).map(User -> new ResponseEntity<>(User,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Getting the data in sorted order
    @GetMapping("/getsortedusers/{field}")
    public List<User> getSortedUsers(@PathVariable String field)
    {
        return obj.UserSorted(field);
    }

    //Getting the data by specifying page number and size
    @GetMapping("/get/{pagenumber}/{pagesize}")
    public ResponseEntity<Page<User>>GetPageUser(@PathVariable int pagenumber,@PathVariable int pagesize)
    {
        return new ResponseEntity<>(obj.UserPagination(pagenumber,pagesize),HttpStatus.OK);
    }

    //Getting the data by implementing both paginationa and sorting
     @GetMapping("/{pagenumber}/{pagesize}/{field}")
     public Page<User> pagesorting(@PathVariable int pagenumber,@PathVariable int pagesize,@PathVariable String field)
     {
         return obj.UserPageSort(pagesize,pagenumber, field);
     }

}
