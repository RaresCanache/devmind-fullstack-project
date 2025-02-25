package org.example.backend.service_interface;

import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.response_DTOs.BankAccountResponseDto;
import org.example.backend.updateDTOs.BankAccountUpdateDto;

import java.util.List;

public interface BankAccountService {
    BankAccountResponseDto getBankAccountById(Integer bankAccountId);

    List<BankAccountResponseDto> getAllBankAccountsByUserId(Integer userId);

    BankAccountResponseDto createBankAccount(BankAccountDto bankAccountDto);

    void updateBankAccountById(Integer bankAccountId, BankAccountUpdateDto bankAccountUpdateDto);

    void deleteBankAccountById(Integer bankAccountId);

    void deleteAllBankAccountsByUserId(Integer userId);
}
