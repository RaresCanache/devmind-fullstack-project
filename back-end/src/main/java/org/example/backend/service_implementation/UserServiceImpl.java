package org.example.backend.service_implementation;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.UserDto;
import org.example.backend.exception_handlers.UserNotFoundException;
import org.example.backend.mappers.UserMapper;
import org.example.backend.models.User;
import org.example.backend.repositories.UserRepository;
import org.example.backend.service_interface.UserService;
import org.example.backend.updateDTOs.UserUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public User createUser(UserDto userDto) {
        User user = userMapper.toModel(userDto);
        return userRepository.save(user);
    }

    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUserById(Integer userId, UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));
        userMapper.updateUserFromDto(userUpdateDto, existingUser);

        userRepository.save(existingUser);
    }

    @Override
    public void deleteUserById(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
