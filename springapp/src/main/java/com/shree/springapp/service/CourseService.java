package com.shree.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shree.springapp.entities.Course;
import com.shree.springapp.repository.CourseRepo;


@Service
public class CourseService {
    @Autowired
    CourseRepo courseobj;

    //post new data
    public Course createCourse(Course a)
    {
        return courseobj.save(a);
    }

    //get all data
    public List<Course> getAllCourses()
    {
        return courseobj.findAll();
    }

    //get data by id
    public Optional<Course> getCourseById(int id)
    {
        return courseobj.findById(id);
    }

    //get data by course
    public Optional<Course> getCourseByCategory(String category)
    {
        return courseobj.findByCategory(category);
    }

    //update data by id
    public Course updateCourse(int id,Course newCourse)
    {
        return courseobj.findById(id).map(existingCourse -> {
            existingCourse.setId(newCourse.getId());
            existingCourse.setTitle(newCourse.getTitle());
            existingCourse.setCategory(newCourse.getCategory());
            existingCourse.setDescription(newCourse.getDescription());
            existingCourse.setDifficulty_level(newCourse.getDifficulty_level());
            existingCourse.setDuration(newCourse.getDuration());
            existingCourse.setInstructor_id(newCourse.getInstructor_id());
            existingCourse.setIs_live(newCourse.getIs_live());
            existingCourse.setPrice(newCourse.getPrice());
            existingCourse.setRating(newCourse.getRating());
            existingCourse.setSchedule(newCourse.getSchedule());
            existingCourse.setStatus(newCourse.getStatus());
            return courseobj.save(existingCourse);

        }).orElseThrow(() -> new RuntimeException("Course not found with id"+id));
    }

    //delete data by id
    public void deleteCourse(int id)
    {
        if(courseobj.existsById(id))
        {
            courseobj.deleteById(id);
        }
        else{
            throw new RuntimeException("Course not found with id"+id);
        }
    }

    //sorting
    public List<Course> CourseSorted(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return courseobj.findAllSort(sort);
    }

    //pagination
    public Page<Course> CoursePagination(int pagesize,int pagenumber)
    {
        Pageable page=PageRequest.of(pagenumber, pagesize);
        return courseobj.findAllPage(page);
    }

    //page & sort
    public Page<Course> CoursePageSort(int pageSize,int pageNumber,String field)
    {
        return courseobj.findAllPageSort(PageRequest.of(pageNumber, pageSize).
        withSort(Sort.by(Sort.Direction.ASC,field)));
    }
    

}
