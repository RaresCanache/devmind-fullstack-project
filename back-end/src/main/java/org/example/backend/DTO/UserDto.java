package org.example.backend.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "Should not be null or blank")
    @Size(max = 45)
    private String username;

    @NotBlank(message = "Should not be null or blank")
    @Size(max = 45)
    private String firstName;

    @NotBlank(message = "Should not be null or blank")
    @Size(max = 45)
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "password must have digit + lowercase + uppercase + punctuation + symbol")
    private String password;

    @AssertFalse(message = "Must be non-premium at first. It can be changed afterwards")
    private boolean isPremium;
}
