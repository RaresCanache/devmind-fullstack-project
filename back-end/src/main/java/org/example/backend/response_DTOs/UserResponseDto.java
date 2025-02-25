package org.example.backend.response_DTOs;

import lombok.Data;

@Data
public class UserResponseDto {
    private String username;
    private String email;
    private String fullName;
    private String avatarUrl;
    private boolean premium;

}
