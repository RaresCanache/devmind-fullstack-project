package org.example.backend.service_interface;

import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.models.BankAccount;

import java.util.List;

public interface BankAccountService {
    BankAccount getBankAccountById(Integer bankAccountId);

    List<BankAccount> getAllBankAccountsByUserId(Integer userId);

    BankAccount createBankAccount(BankAccountDto bankAccountDto);

    void updateBankAccount(Integer bankAccountId, BankAccountDto bankAccountDto);

    void deleteBankAccountById(Integer bankAccountId);

    void deleteAllBankAccountsByUserId(Integer userId);
}
