package org.example.backend.repositories;

import org.example.backend.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
}
