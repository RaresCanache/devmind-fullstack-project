package org.example.backend.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "Should not be null or blank")
    private String username;

    @NotBlank(message = "Should not be null or blank")
    private String firstName;

    @NotBlank(message = "Should not be null or blank")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).+$",
            message = "password must have digit + lowercase + uppercase + punctuation or symbol")
    private String password;

    @NotBlank(message = "Should not be null or blank")
    private String avatarUrl;

    @AssertFalse(message = "Must be non-premium at first. It can be changed afterwards")
    private boolean premium = false;
}
