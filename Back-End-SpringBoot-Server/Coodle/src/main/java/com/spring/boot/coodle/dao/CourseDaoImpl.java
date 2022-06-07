
package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.Course;
import com.spring.boot.coodle.repository.CourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl implements CourseDao{
    
    @Autowired
    CourseRepository courseRepository;
    
    public List<Course> findAllCourses(){
        
        return (courseRepository.findAll());
    }
    
    public Course save(Course course){
        return (courseRepository.save(course));
    }
    
    public Course update(int id, Course course){
        
        Course myCourse = new Course();
        /**
         * Update by field
         * You can find it based the fields
         */
        myCourse = course;
        return (courseRepository.save(course));
    }
    
    public void delete (int id){
        courseRepository.deleteById(id);
    }
}
