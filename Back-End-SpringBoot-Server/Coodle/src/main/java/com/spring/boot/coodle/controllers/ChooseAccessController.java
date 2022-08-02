package com.spring.boot.coodle.controllers;

import com.spring.boot.coodle.entities.Assignment;
import com.spring.boot.coodle.entities.Course;
import com.spring.boot.coodle.entities.ERole;
import com.spring.boot.coodle.entities.Trainer;
import com.spring.boot.coodle.entities.User;
import com.spring.boot.coodle.entities.dto.requests.TrainerRequest;
import com.spring.boot.coodle.entities.dto.responses.MessageResponse;
import com.spring.boot.coodle.entities.dto.responses.TrainerListResponse;
import com.spring.boot.coodle.entities.dto.responses.TrainerResponse;
import com.spring.boot.coodle.entities.dto.responses.UserResponseTable;
import com.spring.boot.coodle.services.AssignmentServiceImpl;
import com.spring.boot.coodle.services.CourseServiceImpl;
import com.spring.boot.coodle.services.TrainerServiceImpl;
import com.spring.boot.coodle.services.UserDetailsServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/choose-access")
public class ChooseAccessController {

    private static final String nullMessage = "Null values are not allowed.";
    private static final String trainerSaved = "Trainer saved with success.";
    private static final String deletedMessage = "Trainer deleted with success.";

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private AssignmentServiceImpl assignmentService;

    @Autowired
    private TrainerServiceImpl trainerService;

    @Autowired
    PasswordEncoder encoder;

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
        List<Trainer> findAllTrainers = trainerService.findAllTrainers();
        List<TrainerListResponse> trainers = userService.findAllUserTrainers(findAllTrainers);

        return (new ResponseEntity(trainers, HttpStatus.OK));
    }

    @PostMapping("/trainer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> save(@RequestBody TrainerRequest trainerRequest) {
        if (null != trainerRequest) {
            trainerRequest.setPassword(encoder.encode(trainerRequest.getPassword()));
            trainerService.save(trainerRequest);
            return (new ResponseEntity(new MessageResponse(trainerSaved),
                    HttpStatus.CREATED));
        } else {
            return (new ResponseEntity(new MessageResponse(nullMessage),
                    HttpStatus.BAD_REQUEST));
        }
    }

    @GetMapping("/trainer/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> get(@PathVariable int id) {
        
        return (new ResponseEntity(setTrainerResponse(id),
                HttpStatus.OK));
    }

    public TrainerResponse setTrainerResponse(int id) {
        User user = userService.findById(id);
        Trainer trainer = trainerService.findById(id);   
        TrainerResponse trainerResponse = new TrainerResponse();
        
        trainerResponse.setId(id);
        trainerResponse.setEmail(user.getEmail());
        trainerResponse.setFirstName(user.getFirstName());
        trainerResponse.setLastName(user.getLastName());
        trainerResponse.setSubject(trainer.getSubbject());
        return (trainerResponse);
    }

    @DeleteMapping("/trainer/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        System.err.println("Id" + id);
        userService.delete(id);
        return (new ResponseEntity(new MessageResponse(deletedMessage),
                HttpStatus.OK));
    }

    @PutMapping("/trainer")
    public ResponseEntity<?> update(@RequestBody TrainerRequest trainerRequest) {
        trainerService.update(1, trainerRequest);
        return (new ResponseEntity(new MessageResponse(nullMessage),
                HttpStatus.BAD_REQUEST));
    }

}
