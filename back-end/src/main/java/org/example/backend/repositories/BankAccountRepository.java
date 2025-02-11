package org.example.backend.repositories;

import org.example.backend.models.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    List<BankAccount> findAllByUser_Id(Integer userId);

    void deleteAllByUser_Id(Integer userId);
}
