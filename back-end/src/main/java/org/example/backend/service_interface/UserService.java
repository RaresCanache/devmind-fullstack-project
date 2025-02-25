package org.example.backend.service_interface;

import org.example.backend.DTOs.UserDto;
import org.example.backend.response_DTOs.UserResponseDto;
import org.example.backend.security.loginDto.TokenResponseDto;
import org.example.backend.updateDTOs.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserDto userDto);

    UserResponseDto getUserById(Integer userId);

    UserResponseDto getUserByEmail(String email);

    List<UserResponseDto> getAllUsers();

    TokenResponseDto authenticate(String email, String password);

    void userExistsById(Integer userId);

    void updateUserById(Integer userId, UserUpdateDto userUpdateDto);

    void deleteUserById(Integer userId);

    void deleteAllUsers();
}
