package org.example.backend.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//TODO UserLoginMock este de fapt entitatea User, de schimbat peste tot unde e folosit
@Getter
@Setter
@AllArgsConstructor
public class UserLoginMock {
    private String username;
    private String password;
    private List<String> roles;
}
