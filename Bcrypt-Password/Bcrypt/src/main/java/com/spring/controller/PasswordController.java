package com.spring.controller;

import com.spring.model.Password;
import com.spring.service.PasswordServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PasswordController {
    
    @GetMapping("/password")
    public ResponseEntity<?> encryptPassword(@RequestBody Password passwordRequest) {
        PasswordServiceImpl passworService = new PasswordServiceImpl();
        return new ResponseEntity<>(passworService.encryptPassword(passwordRequest), HttpStatus.OK);
    }
}
