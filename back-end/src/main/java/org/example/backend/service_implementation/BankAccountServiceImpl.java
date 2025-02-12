package org.example.backend.service_implementation;

import jakarta.transaction.Transactional;
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
import org.example.backend.updateDTOs.BankAccountUpdateDto;
import org.springframework.stereotype.Service;

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
    @Transactional
    public BankAccount getBankAccountById(Integer bankAccountId) {
        return bankAccountRepository.findById(bankAccountId).orElseThrow(() -> new BankAccountNotFoundException("Bank account with id: " + bankAccountId + " not found"));
    }

    @Override
    @Transactional
    public List<BankAccount> getAllBankAccountsByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }

        return bankAccountRepository.findAllByUser_Id(userId);
    }

    @Override
    public void updateBankAccountById(Integer bankAccountId, BankAccountUpdateDto bankAccountUpdateDto) {
        BankAccount existingBankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account with id: " + bankAccountId + " not found"));
        bankAccountMapper.updateBankAccountFromDto(bankAccountUpdateDto, existingBankAccount);
        bankAccountRepository.save(existingBankAccount);
    }

    @Override
    @Transactional
    public void deleteBankAccountById(Integer bankAccountId) {
        if (!bankAccountRepository.existsById(bankAccountId)) {
            throw new BankAccountNotFoundException("Bank account with id: " + bankAccountId + " not found");
        }
        bankAccountRepository.deleteById(bankAccountId);
    }

    @Override
    @Transactional
    public void deleteAllBankAccountsByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }
        bankAccountRepository.deleteAllByUser_Id(userId);
    }
}
