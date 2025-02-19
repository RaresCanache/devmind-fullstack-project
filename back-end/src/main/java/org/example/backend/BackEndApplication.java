package org.example.backend;

import lombok.RequiredArgsConstructor;
import org.example.backend.savings_logic.SavingsService;
import org.example.backend.security.UserLoginMock;
import org.example.backend.security.security_services.UserLoginService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BackEndApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

    private final SavingsService savingsService;

    private final UserLoginService userLoginService;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(savingsService.computeAmountPerDayForUserIdAndBankAccountId(1, 1));

        userLoginService.addUser(new UserLoginMock("admin", passwordEncoder.encode("admin"), List.of("ROLE_ADMIN")));
        userLoginService.addUser(new UserLoginMock("user", passwordEncoder.encode("password"), List.of("ROLE_USER")));
    }
}