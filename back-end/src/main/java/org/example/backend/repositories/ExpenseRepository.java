package org.example.backend.repositories;

import org.example.backend.models.Expense;
import org.example.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findAllByUser_Id(Integer userId);

    void deleteAllByUser_Id(Integer userId);


}
