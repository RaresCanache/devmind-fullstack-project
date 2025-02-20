package org.example.backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.UserDto;
import org.example.backend.models.User;
import org.example.backend.security.loginDto.UserLoginDto;
import org.example.backend.service_interface.UserService;
import org.example.backend.updateDTOs.UserUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return userService.authenticate(userLoginDto.getEmail(), userLoginDto.getPassword());
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
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
