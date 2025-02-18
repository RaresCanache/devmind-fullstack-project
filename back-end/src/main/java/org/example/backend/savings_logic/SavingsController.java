package org.example.backend.savings_logic;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/savings")
@RequiredArgsConstructor
public class SavingsController {
    //TODO Implement SavingsController
    private final SavingsService savingsService;
}
