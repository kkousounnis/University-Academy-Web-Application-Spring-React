package com.spring.boot.coodle.entities.dto.requests;

import com.spring.boot.coodle.constraints.ValidPassword;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {

    @NotBlank
    private String token;

    @NotBlank
    @Size(min = 8, max = 255)
    @ValidPassword
    private String password;

}
