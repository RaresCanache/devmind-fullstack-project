package org.example.backend;

import lombok.RequiredArgsConstructor;
import org.example.backend.savings_logic.SavingsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@RequiredArgsConstructor
public class BackEndApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

    private final SavingsService savingsService;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(savingsService.computeAmountPerDayForUserIdAndBankAccountId(1, 1));
    }
}