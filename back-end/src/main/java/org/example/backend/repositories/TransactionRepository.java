package org.example.backend.repositories;

import org.example.backend.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByBankAccount_Id(Integer bankAccountId);

    void deleteAllByBankAccount_Id(Integer bankAccountId);
}
