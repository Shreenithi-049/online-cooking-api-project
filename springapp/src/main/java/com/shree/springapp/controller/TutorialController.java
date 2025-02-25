package com.shree.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.shree.springapp.entities.Tutorial;

import com.shree.springapp.service.TutorialService;

@RestController
public class TutorialController {
    @Autowired
    TutorialService tobj;

    //Posting new data
    @PostMapping("/api/tutorials")
    public ResponseEntity<Tutorial> addTutorial(@RequestBody Tutorial a)
    {
        return new ResponseEntity<>(tobj.createTutorial(a),HttpStatus.CREATED);
    }

    //Getting all tha data
    @GetMapping("/api/tutorials/all")
    public ResponseEntity<List<Tutorial>>getAllTutorials()
    {
        return new ResponseEntity<>(tobj.getAllTutorials(),HttpStatus.OK);
    }

    //Updating the data by id
    @PutMapping("/api/tutorials/{id}")
    public ResponseEntity<Tutorial>updateTutorial(@PathVariable int id,@RequestBody Tutorial newTutorial)
    {
        try{
            return new ResponseEntity<>(tobj.updateTutorial(id,newTutorial),HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deleting the data by id
    @DeleteMapping("/api/tutorials/{id}")
    public ResponseEntity<Void>deleteTutorial(@PathVariable int id)
    {
        try{
            tobj.deleteTutorial(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(RuntimeException e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    //Getting the data by id
    @GetMapping("/api/tutorial/{id}")
    public ResponseEntity<Tutorial>getTutorialById(@PathVariable int id)
    {
        return tobj.getTutorialById(id).map(Tutorial -> new ResponseEntity<>(Tutorial,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Getting the data by resource_link
    @GetMapping("/api/tutorials/{resource_link}")
    public ResponseEntity<Tutorial>getTutorialByResource_link(@PathVariable String resource_link)
    {
        return tobj.getTutorialByResource_link(resource_link).map(Tutorial -> new ResponseEntity<>(Tutorial,HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Getting the data in sorted order
    @GetMapping("/getsortedtutorials/{field}")
    public List<Tutorial> getSortedTutorials(@PathVariable String field)
    {
        return tobj.TutorialSorted(field);
    }

    //Getting the data by specifying page number and size
    @GetMapping("/tutorial/get/{pagenumber}/{pagesize}")
    public ResponseEntity<Page<Tutorial>>GetPageTutorial(@PathVariable int pagenumber,@PathVariable int pagesize)
    {
        return new ResponseEntity<>(tobj.TutorialPagination(pagenumber,pagesize),HttpStatus.OK);
    }

    //Getting the data by implementing both paginationa and sorting
    @GetMapping("/api/tutorial/{pagenumber}/{pagesize}/{field}")
    public Page<Tutorial> pagesorting(@PathVariable int pagenumber,@PathVariable int pagesize,@PathVariable String field)
    {
        return tobj.TutorialPageSort(pagesize,pagenumber, field);
    }


}
