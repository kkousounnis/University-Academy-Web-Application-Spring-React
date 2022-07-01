package com.spring.model;

import com.spring.constraints.ValidPassword;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Password {
    
    @NotBlank
    @Size(min = 8, max = 255)
    @ValidPassword
    private String password;

}
