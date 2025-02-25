package org.example.backend.savings_logic;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/savings")
@RequiredArgsConstructor
public class SavingsController {
    private final SavingsService savingsService;

    @GetMapping("/calculate")
    public ResponseEntity<List<Integer>> handleSavingsCalculation(@RequestParam Integer userId, @RequestParam Integer bankAccountId) {
        return ResponseEntity.ok(savingsService.computeAmountPerDayForUserIdAndBankAccountId(userId, bankAccountId));
    }
}
