package com.spring.boot.coodle.services;

import com.spring.boot.coodle.dao.CourseDao;
import com.spring.boot.coodle.entities.Course;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    public List<Course> findAllCourses() {
        return (courseDao.findAllCourses());
    }
}
