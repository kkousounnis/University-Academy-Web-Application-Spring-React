
package com.spring.boot.coodle.entities.dto.responses;

import com.spring.boot.coodle.entities.Assignment;
import com.spring.boot.coodle.entities.Course;

public class UserResponseTable {
    private Course course;
      
    private Assignment assignment;

    public UserResponseTable(Course course, Assignment assignment) {
        this.course = course;
        this.assignment = assignment;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
    
}
