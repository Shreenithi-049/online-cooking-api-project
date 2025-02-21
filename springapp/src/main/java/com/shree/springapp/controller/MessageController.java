package com.shree.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.shree.springapp.service.messageService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;








@RestController
public class MessageController {
    @Autowired
    messageService obj;
    @PostMapping("/post")
    public ResponseEntity<message> addMessage(@RequestBody message a)
    {
        return new ResponseEntity<>(obj.add(a),HttpStatus.OK);
    }
    @GetMapping("/messages")
    public ResponseEntity<List<message>>getAllMessages()
    {
        return new ResponseEntity<>(obj.getAllMessages(),HttpStatus.OK);
    }
    @GetMapping("/messages/{id}")
    public ResponseEntity<message>getMessageById(@PathVariable int id)
    {
        return obj.getMessageById(id).map(message -> new ResponseEntity<>(message,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/messages/{id}")
    public ResponseEntity<message>updateMessage(@PathVariable int id,@RequestBody message newMessage)
    {
        try{
            return new ResponseEntity<>(obj.updateMessage(id, newMessage),HttpStatus.OK);
        }
        catch(RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void>deleteMessage(@PathVariable int id)
    {
        try{
            obj.deleteMessage(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/api/message/sortBy/{field}")
    public List<message> g(@PathVariable String field)
    {
        return obj.sort(field);
    }

    @GetMapping("/api/message/{offset}/{pagesize}")
    public List<message> get(@PathVariable int offset,@PathVariable int pagesize)
    {
        return obj.page(pagesize, offset);
    }

    @GetMapping("/api/message/{offset}/{pagesize}/{field}")
    public List<message> pagesorting(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
    {
        return obj.pagesort(pagesize,offset, field);
    }
    

    
    

    
    
    
}
