package com.spring.boot.coodle.entities.dto.requests;

import com.spring.boot.coodle.constraints.ValidPassword;
import java.util.Set;

import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {

    @NotBlank
    @Size(max = 50)
    @Email(message = "Email is not valid",
            regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'"
            + "*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e"
            + "-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09"
            + "\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]"
            + "*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:("
            + "?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]"
            + "|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01"
            + "-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x"
            + "01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @ValidPassword
    private String password;

    @NotNull
    @Size(min = 3, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 20)
    private String lastName;

    private Set<String> role;

}
