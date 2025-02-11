package org.example.backend.service_interface;

import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.models.Expense;

import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpensesByUserId(Integer userId);

    Expense getExpenseById(Integer expenseId);

    Expense createExpense(ExpenseDto expenseDto);

    void deleteExpenseById(Integer expenseId);

    void deleteAllExpensesByUserId(Integer userId);

    void updateExpense(Integer expenseId, ExpenseDto expenseDto);
}
