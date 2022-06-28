package com.spring.boot.coodle.repository;

import com.spring.boot.coodle.entities.Course;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
