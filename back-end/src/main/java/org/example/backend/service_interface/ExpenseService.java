package org.example.backend.service_interface;

import org.example.backend.DTO.ExpenseDto;
import org.example.backend.models.Expense;

import java.util.List;

public interface ExpenseService {
    List<Expense> getAllExpensesByUserId(Integer userId);

    Expense createExpense(ExpenseDto expenseDto);
}
