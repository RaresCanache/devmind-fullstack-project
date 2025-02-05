package org.example.backend.services;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.ExpenseDto;
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

    private final UserRepository userRepository;

    @Override
    public List<Expense> getAllExpensesByUserId(Integer userId) {
        return expenseRepository.findAllByUser_Id(userId);
    }

    @Override
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
