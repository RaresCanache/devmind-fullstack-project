package org.example.backend.security.security_services;

import lombok.RequiredArgsConstructor;
import org.example.backend.security.UserLoginMock;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLoginService {
    //TODO Am baza de date, nu mai trebuie o lista de useri, de sters
    private static final List<UserLoginMock> userList = new ArrayList<>();

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public void addUser(UserLoginMock userLoginMock) {
        userList.add(userLoginMock);
    }
    //TODO Stream ul este de fapt findById din UserServiceImpl, de inlocuit
    public String authenticate(String username, String password) {
        UserLoginMock user = userList.stream()
                .filter(u -> u.getUsername().equals(username))
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .findFirst().orElse(null);

        if (null != user) {
            return jwtService.createToken(username);
        }

        throw new UsernameNotFoundException("Invalid username or password");
    }

    //TODO inlocuieste UserLoginMock cu entitatea User
    public UserLoginMock validateUser(String token) {
        String username = jwtService.validateToken(token);

        return userList.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void checkMyCredentials() {
        System.out.println("Congrats, you were able to execute this method");
    }
}
