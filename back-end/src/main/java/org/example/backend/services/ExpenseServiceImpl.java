package org.example.backend.services;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.mappers.ExpenseMapper;
import org.example.backend.models.Expense;
import org.example.backend.models.User;
import org.example.backend.repositories.ExpenseRepository;
import org.example.backend.repositories.UserRepository;
import org.example.backend.service_interface.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    private final ExpenseMapper expenseMapper;

    private final UserRepository userRepository;

    @Override
    public List<Expense> getAllExpensesByUserId(Integer userId) {
        return expenseRepository.findAllByUser_Id(userId);
    }

    @Override
    public Expense createExpense(ExpenseDto expenseDto) {
        Expense expense = expenseMapper.toModel(expenseDto);
        User user = userRepository.findById(expenseDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        expense.setUser(user);

        return expenseRepository.save(expense);
    }
}
