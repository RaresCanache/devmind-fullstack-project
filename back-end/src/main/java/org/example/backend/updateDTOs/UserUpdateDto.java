package org.example.backend.updateDTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserUpdateDto {
    @Size(max = 45)
    private String username;

    @Size(max = 45)
    private String firstName;

    @Size(max = 45)
    private String lastName;

    @Email
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "password must have digit + lowercase + uppercase + punctuation + symbol")
    private String password;

    private String avatarUrl;

    private boolean premium;
}
