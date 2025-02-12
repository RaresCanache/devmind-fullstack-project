package org.example.backend.service_interface;

import org.example.backend.DTOs.TransactionDto;
import org.example.backend.models.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactionsByBankAccountId(Integer bankAccountId);

    Transaction getTransactionById(Integer transactionId);

    Transaction createTransaction(TransactionDto transactionDto);

    void deleteTransactionById(Integer transactionId);

    void deleteAllTransactionsByBankAccountId(Integer bankAccountId);
}
