package org.example.backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.UserDto;
import org.example.backend.models.User;
import org.example.backend.response_DTOs.UserResponseDto;
import org.example.backend.security.loginDto.TokenResponseDto;
import org.example.backend.security.loginDto.UserLoginDto;
import org.example.backend.service_interface.UserService;
import org.example.backend.updateDTOs.UserUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(userService.authenticate(userLoginDto.getEmail(), userLoginDto.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping ("/update/{userId}")
    public ResponseEntity<String> updateUserById(@PathVariable Integer userId, @Valid @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUserById(userId, userUpdateDto);
        return ResponseEntity.ok("Successfully updated user");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted user");
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted all users");
    }
}
