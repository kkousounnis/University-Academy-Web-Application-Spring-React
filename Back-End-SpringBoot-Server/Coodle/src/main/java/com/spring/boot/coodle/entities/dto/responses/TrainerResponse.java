package com.spring.boot.coodle.entities.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerResponse {
    
    private Integer id;
    
    private String email;
    
    private String firstName;
    
    private String lastName;
    
    private String subject;
    
}
