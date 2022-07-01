package com.spring.controller;

import com.spring.constraints.PasswordConstraintValidator;
import com.spring.model.Password;
import com.spring.service.PasswordServiceImpl;
import javax.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PasswordController {
    
    @PostMapping("/password")
    public ResponseEntity<?> encryptPassword(@Validated @RequestBody Password passwordRequest) { 
        System.err.println(passwordRequest+"NOt working");
        PasswordServiceImpl passworService = new PasswordServiceImpl();
        return new ResponseEntity<>(passworService.encryptPassword(passwordRequest), HttpStatus.OK);
    }
}
