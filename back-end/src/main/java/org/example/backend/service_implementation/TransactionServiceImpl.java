package org.example.backend.service_implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.TransactionDto;
import org.example.backend.exception_handlers.BankAccountNotFoundException;
import org.example.backend.exception_handlers.TransactionNotFoundException;
import org.example.backend.mappers.TransactionMapper;
import org.example.backend.models.BankAccount;
import org.example.backend.models.Transaction;
import org.example.backend.repositories.BankAccountRepository;
import org.example.backend.repositories.TransactionRepository;
import org.example.backend.service_interface.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    private final BankAccountRepository bankAccountRepository;

    private final TransactionMapper transactionMapper;

    @Override
    public Transaction getTransactionById(Integer transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id: " + transactionId + " not found"));
    }

    @Override
    public List<Transaction> getAllTransactionsByBankAccountId(Integer bankAccountId) {
        if (!bankAccountRepository.existsById(bankAccountId)) {
            throw new BankAccountNotFoundException("Bank account with id: " + bankAccountId + " not found");
        }
        return transactionRepository.findAllByBankAccount_Id(bankAccountId);
    }

    @Override
    public Transaction createTransaction(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toModel(transactionDto);
        BankAccount bankAccount = bankAccountRepository.findById(transactionDto.getBankAccountId())
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account with id: " + transactionDto.getBankAccountId() + " not found"));
        transaction.setBankAccount(bankAccount);

        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransactionById(Integer transactionId) {
        if (!transactionRepository.existsById(transactionId)) {
            throw new TransactionNotFoundException("Transaction with id: " + transactionId + " not found");
        }
        transactionRepository.deleteById(transactionId);
    }

    @Override
    @Transactional
    public void deleteAllTransactionsByBankAccountId(Integer bankAccountId) {
        if (!bankAccountRepository.existsById(bankAccountId)) {
            throw new BankAccountNotFoundException("Bank account with id: " + bankAccountId + " not found");
        }
        transactionRepository.deleteAllByBankAccount_Id(bankAccountId);
    }
}
