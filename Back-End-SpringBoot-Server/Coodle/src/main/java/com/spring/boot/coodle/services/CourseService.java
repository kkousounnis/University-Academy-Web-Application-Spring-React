
package com.spring.boot.coodle.services;

import com.spring.boot.coodle.entities.Course;
import java.util.List;

public interface CourseService {
    
    public List<Course> findAllCourses();   
}
