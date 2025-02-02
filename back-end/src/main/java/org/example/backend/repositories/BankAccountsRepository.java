package org.example.backend.repositories;

import org.example.backend.models.BankAccounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountsRepository extends JpaRepository<BankAccounts, Integer> {
}
