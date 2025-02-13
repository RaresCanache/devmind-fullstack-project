package org.example.backend.service_interface;

import org.example.backend.DTOs.UserDto;
import org.example.backend.models.User;
import org.example.backend.updateDTOs.UserUpdateDto;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);

    User findUserById(Integer userId);

    List<User> findAllUsers();

    void updateUserById(Integer userId, UserUpdateDto userUpdateDto);

    void deleteUserById(Integer userId);

    void deleteAllUsers();
}
