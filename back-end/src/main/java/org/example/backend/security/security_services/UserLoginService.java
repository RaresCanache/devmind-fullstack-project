package org.example.backend.security.security_services;

import lombok.RequiredArgsConstructor;
import org.example.backend.models.User;
import org.example.backend.security.UserLoginMock;
import org.example.backend.service_interface.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;

    public String authenticate(String email, String password) {
        User user = userService.getUserByEmail(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        return jwtService.createToken(email);
    }

    //TODO inlocuieste UserLoginMock cu entitatea User
    public User validateUser(String token) {
        String email = jwtService.validateToken(token);

        return userService.getUserByEmail(email);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void checkMyCredentials() {
        System.out.println("Congrats, you were able to execute this method");
    }
}
