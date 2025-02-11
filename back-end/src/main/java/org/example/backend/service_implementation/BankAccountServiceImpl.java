package org.example.backend.service_implementation;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.exception_handlers.BankAccountNotFoundException;
import org.example.backend.exception_handlers.UserNotFoundException;
import org.example.backend.mappers.BankAccountMapper;
import org.example.backend.models.BankAccount;
import org.example.backend.models.User;
import org.example.backend.repositories.BankAccountRepository;
import org.example.backend.repositories.UserRepository;
import org.example.backend.service_interface.BankAccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    private final UserRepository userRepository;

    private final BankAccountMapper bankAccountMapper;

    @Override
    public BankAccount createBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = bankAccountMapper.toModel(bankAccountDto);
        User user = userRepository.findById(bankAccountDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id: " + bankAccountDto.getUserId() + " not found"));
        bankAccount.setUser(user);

        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount getBankAccountById(Integer bankAccountId) {
        return bankAccountRepository.findById(bankAccountId).orElseThrow(() -> new BankAccountNotFoundException("Bank account with id: " + bankAccountId + " not found"));
    }

    @Override
    public List<BankAccount> getAllBankAccountsByUserId(Integer userId) {
        if (userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }

        return bankAccountRepository.findAllByUser_Id(userId);
    }

    @Override
    public void updateBankAccount(Integer bankAccountId, BankAccountDto bankAccountDto) {
        BankAccount existingBankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(() -> new BankAccountNotFoundException("Bank account with id: " + bankAccountId + " not found"));

        if (bankAccountDto.getBankName() != null) {
            existingBankAccount.setBankName(bankAccountDto.getBankName());
        }
        if (bankAccountDto.getCurrency() != null && bankAccountDto.getCurrency().length() == 3) {
            existingBankAccount.setCurrency(bankAccountDto.getCurrency());
        }
        if (bankAccountDto.getBalance() != null && bankAccountDto.getBalance().compareTo(BigDecimal.ZERO) >= 0) {

        }

    }

    @Override
    public void deleteBankAccountById(Integer bankAccountId) {

    }

    @Override
    public void deleteAllBankAccountsByUserId(Integer userId) {

    }
}
