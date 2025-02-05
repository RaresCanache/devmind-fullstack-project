package org.example.backend.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.models.Expense;
import org.example.backend.services.ExpenseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseServiceImpl expenseService;

    //TODO modify response DTO
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Expense>> getExpenseByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(expenseService.getAllExpensesByUserId(userId));
    }

    @PostMapping("/save")
    public ResponseEntity<Expense> saveExpense(@Valid @RequestBody ExpenseDto expenseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseService.createExpense(expenseDto));
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable Integer expenseId) {
        expenseService.deleteExpenseById(expenseId);
        return ResponseEntity.ok().body("Successfully deleted expense");
    }

    @DeleteMapping("/delete/all/{userId}")
    @Transactional
    public ResponseEntity<String> deleteAllExpensesByUserId(@PathVariable Integer userId) {
        expenseService.deleteAllExpensesByUserId(userId);
        return ResponseEntity.ok().body("Successfully deleted all expenses");
    }
}
