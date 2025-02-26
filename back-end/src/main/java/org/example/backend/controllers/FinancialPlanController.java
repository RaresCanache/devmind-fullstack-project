package org.example.backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.FinancialPlanDto;
import org.example.backend.models.FinancialPlan;
import org.example.backend.service_interface.FinancialPlanService;
import org.example.backend.updateDTOs.FinancialPlanUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/financial-plan")
@RequiredArgsConstructor
public class FinancialPlanController {
    private final FinancialPlanService financialPlanService;

    @GetMapping("/{userId}")
    public ResponseEntity<FinancialPlan> getFinancialPlanById(@PathVariable Integer userId) {
        return ResponseEntity.ok(financialPlanService.getFinancialPlanByUserId(userId));
    }

    @PostMapping("/save")
    public ResponseEntity<FinancialPlan> saveFinancialPlan(@Valid @RequestBody FinancialPlanDto financialPlanDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(financialPlanService.createFinancialPlan(financialPlanDto));
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> replaceFinancialPlanByUserId(@PathVariable Integer userId, @Valid @RequestBody FinancialPlanUpdateDto financialPlanUpdateDto) {
        financialPlanService.replaceFinancialPlanByUserId(userId, financialPlanUpdateDto);
        return ResponseEntity.ok("Successfully replaced financial plan");
    }
}