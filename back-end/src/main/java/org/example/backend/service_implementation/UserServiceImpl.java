package org.example.backend.service_implementation;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.UserDto;
import org.example.backend.exception_handlers.UserNotFoundException;
import org.example.backend.mappers.UserMapper;
import org.example.backend.models.User;
import org.example.backend.repositories.UserRepository;
import org.example.backend.security.security_services.JwtService;
import org.example.backend.service_interface.UserService;
import org.example.backend.updateDTOs.UserUpdateDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public User createUser(UserDto userDto) {
        User user = userMapper.toModel(userDto);

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return jwtService.createToken(email, user.isPremium());
    }

    @Override
    public void userExistsById(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }
    }

    @Override
    public void updateUserById(Integer userId, UserUpdateDto userUpdateDto) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));
        userMapper.updateUserFromDto(userUpdateDto, existingUser);

        if (userUpdateDto.getPassword() != null) {
            existingUser.setPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        }

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
