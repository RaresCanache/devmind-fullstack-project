package org.example.backend.service_interface;

import org.example.backend.DTOs.UserDto;
import org.example.backend.models.User;
import org.example.backend.updateDTOs.UserUpdateDto;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void userExistsById(Integer userId);

    void updateUserById(Integer userId, UserUpdateDto userUpdateDto);

    void deleteUserById(Integer userId);

    void deleteAllUsers();
}
