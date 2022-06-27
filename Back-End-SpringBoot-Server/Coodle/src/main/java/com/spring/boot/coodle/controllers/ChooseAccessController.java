package com.spring.boot.coodle.controllers;

import com.spring.boot.coodle.entities.Assignment;
import com.spring.boot.coodle.entities.Course;
import com.spring.boot.coodle.entities.dto.responses.MessageResponse;
import com.spring.boot.coodle.entities.dto.responses.UserResponseTable;
import com.spring.boot.coodle.services.UserDetailsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/choose-access")
public class ChooseAccessController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @GetMapping("/all")
    public String allAccess() {
        return "Learn without limits";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> userAccess() {

        List<Course> courses = new ArrayList<>();
        List<Assignment> assignments = new ArrayList<>();
        courses = userService.findAllCourses();
        assignments = userService.findAllAssignments();
        return (ResponseEntity.ok(new UserResponseTable(courses, assignments)));
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
