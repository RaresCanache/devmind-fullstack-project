package org.example.backend.service_implementation;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.UserDto;
import org.example.backend.exception_handlers.UserNotFoundException;
import org.example.backend.mappers.UserMapper;
import org.example.backend.models.User;
import org.example.backend.repositories.UserRepository;
import org.example.backend.response_DTOs.UserResponseDto;
import org.example.backend.security.loginDto.TokenResponseDto;
import org.example.backend.security.security_services.JwtService;
import org.example.backend.service_interface.UserService;
import org.example.backend.updateDTOs.UserUpdateDto;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public UserResponseDto createUser(UserDto userDto) {
        User user = userMapper.toModel(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto getUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));

        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found"));

        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public TokenResponseDto authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        String token = jwtService.createToken(email, user.isPremium());

        TokenResponseDto tokenResponseDto = new TokenResponseDto();
        tokenResponseDto.setToken(token);

        return tokenResponseDto;
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
