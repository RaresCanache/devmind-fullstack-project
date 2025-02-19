package org.example.backend.security.loginDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {
    @Email
    private String email;

    @NotBlank(message = "Should not be blank")
    private String password;
}
