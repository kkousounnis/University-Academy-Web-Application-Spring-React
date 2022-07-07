package com.spring.boot.coodle.controllers;

import com.spring.boot.coodle.entities.Assignment;
import com.spring.boot.coodle.entities.Course;
import com.spring.boot.coodle.entities.User;
import com.spring.boot.coodle.entities.dto.responses.TrainerListResponse;
import com.spring.boot.coodle.entities.dto.responses.UserResponseTable;
import com.spring.boot.coodle.services.AssignmentServiceImpl;
import com.spring.boot.coodle.services.CourseServiceImpl;
import com.spring.boot.coodle.services.TrainerServiceImpl;
import com.spring.boot.coodle.services.UserDetailsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    
    @Autowired
    private CourseServiceImpl courseService;
    
    @Autowired
    private AssignmentServiceImpl assignmentService;
    
    @Autowired
    private TrainerServiceImpl trainerService;
    
    @GetMapping("/all")
    public String allAccess() {
        return "Learn without limits";
    }
    
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> userAccess() {
        
        List<Course> courses = new ArrayList<>();
        List<Assignment> assignments = new ArrayList<>();
        courses = courseService.findAllCourses();
        assignments = assignmentService.findAllAssignments();
        return new ResponseEntity(new UserResponseTable(courses, assignments), HttpStatus.OK);
    }
    
    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Administrator Dashboard";
    }
    
    @GetMapping("/trainers-list")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getListOfTrainers() {
        TrainerListResponse trainerListResponse = new TrainerListResponse();
        List<TrainerListResponse> trainers = new ArrayList<TrainerListResponse>();
        List<User> users = new ArrayList<User>();
        System.err.println("User");
        for (User user : userService.findAllUsers()) {
            System.err.println("Roles" + user.getRoles().toString().contains("2"));
            if (user.getRoles().toString().contains("2")) {
                System.err.println("User" + users);
                trainerListResponse.setId(user.getId());
                trainerListResponse.setEmail(user.getEmail());
                trainerListResponse.setPassword(user.getPassword());
                trainerListResponse.setFistName(user.getFirstName());
                trainerListResponse.setLastName(user.getLastName());                
                trainers.add(trainerListResponse);
            }
        }
        
        return new ResponseEntity(trainers, HttpStatus.OK);
    }
}
