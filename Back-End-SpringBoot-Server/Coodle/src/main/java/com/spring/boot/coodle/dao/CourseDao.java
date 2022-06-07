
package com.spring.boot.coodle.dao;

import com.spring.boot.coodle.entities.Course;
import java.util.List;

public interface CourseDao {
    
    public List<Course> findAllCourses();
    
    public Course save(Course course);
    
    public Course update(int id, Course course);
    
    public void delete(int id);    
}
