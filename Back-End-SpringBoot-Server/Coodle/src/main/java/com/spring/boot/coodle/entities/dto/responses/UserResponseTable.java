package com.spring.boot.coodle.entities.dto.responses;

import com.spring.boot.coodle.entities.Assignment;
import com.spring.boot.coodle.entities.Course;
import java.util.List;

public class UserResponseTable {

    private List<Course> course;

    private List<Assignment> assignment;

    public UserResponseTable() {
    }

    public UserResponseTable(List<Course> course, List<Assignment> assignment) {
        this.course = course;
        this.assignment = assignment;
    }

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    public List<Assignment> getAssignment() {
        return assignment;
    }

    public void setAssignment(List<Assignment> assignment) {
        this.assignment = assignment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("course=").append(course);
        sb.append(", assignment=").append(assignment);
        sb.append('}');
        return sb.toString();
    }

}
