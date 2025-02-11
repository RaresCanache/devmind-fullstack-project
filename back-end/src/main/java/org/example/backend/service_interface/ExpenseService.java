package org.example.backend.service_interface;

import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.models.Expense;
import org.example.backend.updateDTOs.ExpenseUpdateDto;

import java.util.List;

public interface ExpenseService {

    List<Expense> getAllExpensesByUserId(Integer userId);

    Expense getExpenseById(Integer expenseId);

    Expense createExpense(ExpenseDto expenseDto);

    void updateExpense(Integer expenseId, ExpenseUpdateDto expenseDto);

    void deleteExpenseById(Integer expenseId);

    void deleteAllExpensesByUserId(Integer userId);
}
