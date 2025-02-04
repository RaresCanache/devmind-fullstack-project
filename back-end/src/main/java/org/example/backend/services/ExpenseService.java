package org.example.backend.services;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.ExpenseDto;
import org.example.backend.models.Expense;
import org.example.backend.models.User;
import org.example.backend.repositories.ExpenseRepository;
import org.example.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;

    public List<Expense> getAllExpensesByUserId(Integer userId) {
        return expenseRepository.findAllByUser_Id(userId);
    }

    public Expense createExpense(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        User user = userRepository.findById(expenseDto.getUserId()).orElseThrow();
        expense.setUser(user);
        expense.setType(expenseDto.getType());
        expense.setName(expenseDto.getName());
        expense.setDateExpense(expenseDto.getDateExpense());
        expense.setAmount(expenseDto.getAmount());
        expense.setFrequency(expenseDto.getFrequency());

        return expenseRepository.save(expense);
    }
}
