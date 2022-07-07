package com.spring.boot.coodle.controllers;

import com.spring.boot.coodle.entities.Assignment;
import com.spring.boot.coodle.entities.Course;
import com.spring.boot.coodle.entities.ERole;
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
        List<User> users = new ArrayList<User>();
        trainerListResponse.setTrainer(trainerService.findAllTrainers());
        for (User user : userService.findAllUsers()) {
            if (user.getRoles().equals(ERole.ROLE_MODERATOR)) {
                users.add(user);
            }
        }
        return new ResponseEntity(trainerListResponse, HttpStatus.OK);
    }
}
