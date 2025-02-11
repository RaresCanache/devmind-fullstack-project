package org.example.backend.service_interface;

import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.models.BankAccount;

import java.util.List;

public interface BankAccountService {
    BankAccount createBankAccount(BankAccountDto bankAccountDto);

    List<BankAccount> getAllBankAccountsByUserId(Integer userId);

    
}
