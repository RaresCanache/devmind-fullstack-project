package org.example.backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.TransactionDto;
import org.example.backend.models.Transaction;
import org.example.backend.service_interface.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Integer transactionId) {
        return ResponseEntity.ok(transactionService.getTransactionById(transactionId));
    }

    //TODO modify response DTO
    @GetMapping("/bank-account/{bankAccountId}")
    public ResponseEntity<List<Transaction>> getTransactionByBankAccountId(@PathVariable Integer bankAccountId) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByBankAccountId(bankAccountId));
    }

    @PostMapping("/save")
    public ResponseEntity<Transaction> saveTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(transactionDto));
    }
    
    @DeleteMapping("/delete/{transactionId}")
    public ResponseEntity<String> deleteTransactionById(@PathVariable Integer transactionId) {
        transactionService.deleteTransactionById(transactionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted transaction");
    }

    @DeleteMapping("/delete-all/{bankAccountId}")
    public ResponseEntity<String> deleteAllTransactionsByBankAccountId(@PathVariable Integer bankAccountId) {
        transactionService.deleteAllTransactionsByBankAccountId(bankAccountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted all transactions for bank account: " + bankAccountId);
    }
}
