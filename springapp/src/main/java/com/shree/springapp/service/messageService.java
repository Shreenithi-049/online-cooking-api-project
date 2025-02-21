package com.shree.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shree.springapp.controller.message;
import com.shree.springapp.repository.MessageRepo;

@Service
public class messageService {
    @Autowired
    MessageRepo obj;
    public message add(message a)
    {
        return obj.save(a);
    }
    public List<message> getAllMessages()
    {
        return obj.findAll();
    }
    public Optional<message> getMessageById(int id)
    {
        return obj.findById(id);

    }
    public message updateMessage(int id, message newMessage) 
    {
        return obj.findById(id).map(existingMessage -> {
            existingMessage.setId(newMessage.getId()); 
            existingMessage.setFirstname(newMessage.getFirstname());  
            return obj.save(existingMessage);
        }).orElseThrow(() -> new RuntimeException("Message not found with id " + id));
    }    
    public void deleteMessage(int id)
    {
        if(obj.existsById(id))
        {
            obj.deleteById(id);
        }
        else
        {
            throw new RuntimeException("Message not found with id "+id);
        }
    }
      public List<message> sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return obj.findAll(sort);
    }

    public List<message> page(int pageSize,int pageNumber)
    {
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return obj.findAll(page).getContent();
    }
   
    public List<message> pagesort(int pageSize,int pageNumber,String field)
    {
        return obj.findAll(PageRequest.of(pageNumber, pageSize).
        withSort(Sort.by(Sort.Direction.ASC,field))).getContent();
    }
   

}
