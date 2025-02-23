package org.example.backend.savings_logic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/savings")
@RequiredArgsConstructor
public class SavingsController {
    private final SavingsService savingsService;

    @GetMapping("/calculate")
    public ResponseEntity<List<BigDecimal>> handleSavingsCalculation(Integer userId, Integer bankAccountId) {
        return ResponseEntity.ok(savingsService.computeAmountPerDayForUserIdAndBankAccountId(userId, bankAccountId));
    }
}
