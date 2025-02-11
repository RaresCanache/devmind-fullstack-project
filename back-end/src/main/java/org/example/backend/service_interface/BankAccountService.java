package org.example.backend.service_interface;

import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.models.BankAccount;
import org.example.backend.updateDTOs.BankAccountUpdateDto;

import java.util.List;

public interface BankAccountService {
    BankAccount getBankAccountById(Integer bankAccountId);

    List<BankAccount> getAllBankAccountsByUserId(Integer userId);

    BankAccount createBankAccount(BankAccountDto bankAccountDto);

    void updateBankAccount(Integer bankAccountId, BankAccountUpdateDto bankAccountUpdateDto);

    void deleteBankAccountById(Integer bankAccountId);

    void deleteAllBankAccountsByUserId(Integer userId);
}
