package org.example.backend.repositories;

import org.example.backend.models.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesRepository extends JpaRepository<Expenses, Integer> {
}
