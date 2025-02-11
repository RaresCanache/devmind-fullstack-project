package org.example.backend.service_implementation;

import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.models.BankAccount;
import org.example.backend.service_interface.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Override
    public BankAccount createBankAccount(BankAccountDto bankAccountDto) {
        return null;
    }

    @Override
    public List<BankAccount> getAllBankAccountsByUserId(Integer userId) {
        return List.of();
    }
}
