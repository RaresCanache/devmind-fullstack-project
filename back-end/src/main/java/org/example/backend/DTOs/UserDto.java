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

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "password must have digit + lowercase + uppercase + punctuation + symbol")
    private String password;

    @NotBlank(message = "Should not be null or blank")
    private String avatarUrl;

    @AssertFalse(message = "Must be non-premium at first. It can be changed afterwards")
    private boolean premium = false;
}
