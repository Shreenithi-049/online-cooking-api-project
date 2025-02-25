package com.shree.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.shree.springapp.entities.Tutorial;

import com.shree.springapp.repository.TutorialRepo;

@Service
public class TutorialService {
    @Autowired
    TutorialRepo tobj;

    //post new data
    public Tutorial createTutorial(Tutorial a)
    {
        return tobj.save(a);
    }

    //get all data
    public List<Tutorial> getAllTutorials()
    {
        return tobj.findAll();
    }

    //get data by id
    public Optional<Tutorial> getTutorialById(int id)
    {
        return tobj.findById(id);
    }

    //get data by resource_link
    public Optional<Tutorial> getTutorialByResource_link(String resource_link)
    {
        return tobj.findByResource_link(resource_link);
    }

    //update data by id
    public Tutorial updateTutorial(int id,Tutorial newTutorial)
    {
        return tobj.findById(id).map(existingTutorial -> {
            existingTutorial.setId(newTutorial.getId());
            existingTutorial.setCourse_id(newTutorial.getCourse_id());
            
            return tobj.save(existingTutorial);

        }).orElseThrow(() -> new RuntimeException("Course not found with id"+id));
    }

    //delete data by id
    public void deleteTutorial(int id)
    {
        if(tobj.existsById(id))
        {
            tobj.deleteById(id);
        }
        else{
            throw new RuntimeException("Course not found with id"+id);
        }
    }

    //sorting
    public List<Tutorial> TutorialSorted(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return tobj.findAllSort(sort);
    }

    //pagination
    public Page<Tutorial> TutorialPagination(int pagesize,int pagenumber)
    {
        Pageable page=PageRequest.of(pagenumber, pagesize);
        return tobj.findAllPage(page);
    }

    //page & sort
    public Page<Tutorial> TutorialPageSort(int pageSize,int pageNumber,String field)
    {
        return tobj.findAllPageSort(PageRequest.of(pageNumber, pageSize).
        withSort(Sort.by(Sort.Direction.ASC,field)));
    }
    


}
