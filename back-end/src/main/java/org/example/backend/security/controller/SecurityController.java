package org.example.backend.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.security.loginDto.UserLoginDto;
import org.example.backend.security.security_services.UserLoginService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {
    private final UserLoginService userLoginService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return userLoginService.authenticate(userLoginDto.getUsername(), userLoginDto.getPassword());
    }

    @GetMapping("/whoami")
    public String protectedWhoami() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "You are: " + authentication.getName() + " with authorities " + authentication.getAuthorities();
    }

    @GetMapping("/admin-only")
    public String adminOnly() {
        return "Congrats, you're an admin";
    }

    @GetMapping("/user-only")
    public String userOnly() {
        return "Congrats, you're a user";
    }

    @GetMapping("method-security")
    public String methodSecurity() {
        userLoginService.checkMyCredentials();

        return "Congrats, you managed to access this method";
    }
}
