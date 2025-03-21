package org.example.backend.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.response_DTOs.BankAccountResponseDto;
import org.example.backend.service_interface.BankAccountService;
import org.example.backend.updateDTOs.BankAccountUpdateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-accounts")
@RequiredArgsConstructor
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @GetMapping("/{bankAccountId}")
    public ResponseEntity<BankAccountResponseDto> getBankAccountById(@PathVariable Integer bankAccountId) {
        return ResponseEntity.ok(bankAccountService.getBankAccountById(bankAccountId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BankAccountResponseDto>> getAllBankAccountsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(bankAccountService.getAllBankAccountsByUserId(userId));
    }

    @PostMapping("/save")
    public ResponseEntity<BankAccountResponseDto> saveBankAccount(@Valid @RequestBody BankAccountDto bankAccountDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bankAccountService.createBankAccount(bankAccountDto));
    }

    @PutMapping("/update/{bankAccountId}")
    public ResponseEntity<String> updateBankAccountById(@PathVariable Integer bankAccountId, @Valid @RequestBody BankAccountUpdateDto bankAccountUpdateDto) {
        bankAccountService.updateBankAccountById(bankAccountId, bankAccountUpdateDto);
        return ResponseEntity.ok("Successfully updated bank account");
    }

    @DeleteMapping("/delete/{bankAccountId}")
    public ResponseEntity<String> deleteBankAccountById(@PathVariable Integer bankAccountId) {
        bankAccountService.deleteBankAccountById(bankAccountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted bank account");
    }

    @DeleteMapping("/delete-all/{userId}")
    public ResponseEntity<String> deleteAllBankAccountsByUserId(@PathVariable Integer userId) {
        bankAccountService.deleteAllBankAccountsByUserId(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted all bank accounts for user: " + userId);
    }
}
