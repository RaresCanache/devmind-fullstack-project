package org.example.backend.service_interface;

import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.response_DTOs.ExpenseResponseDto;
import org.example.backend.updateDTOs.ExpenseUpdateDto;

import java.util.List;

public interface ExpenseService {

    ExpenseResponseDto getExpenseById(Integer expenseId);

    ExpenseResponseDto createExpense(ExpenseDto expenseDto);

    List<ExpenseResponseDto> getAllExpensesByUserId(Integer userId);

    void updateExpenseById(Integer expenseId, ExpenseUpdateDto expenseDto);

    void deleteExpenseById(Integer expenseId);

    void deleteAllExpensesByUserId(Integer userId);
}
