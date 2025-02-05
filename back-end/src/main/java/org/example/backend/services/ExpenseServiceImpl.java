package org.example.backend.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.mappers.ExpenseMapper;
import org.example.backend.models.Expense;
import org.example.backend.models.User;
import org.example.backend.repositories.ExpenseRepository;
import org.example.backend.repositories.UserRepository;
import org.example.backend.service_interface.ExpenseService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    private final ExpenseMapper expenseMapper;

    private final UserRepository userRepository;

    @Override
    public List<Expense> getAllExpensesByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found");
        }
        return expenseRepository.findAllByUser_Id(userId);
    }

    @Override
    public Expense createExpense(ExpenseDto expenseDto) {
        Expense expense = expenseMapper.toModel(expenseDto);
        User user = userRepository.findById(expenseDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    @Override
    public void deleteExpenseById(Integer expenseId) {
        if (!expenseRepository.existsById(expenseId)) {
            throw new EntityNotFoundException("Expense not found");
        }
        expenseRepository.deleteById(expenseId);
    }

    @Override
    public void deleteAllExpensesByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found");
        }
        expenseRepository.deleteAllByUser_Id(userId);
    }

    @Override
    public void updateExpense(Integer expenseId, ExpenseDto expenseDto) {
        Expense existingExpense = expenseRepository.findById(expenseId).orElseThrow(() -> new EntityNotFoundException("Expense not found"));

        if (expenseDto.getType() != null) {
            existingExpense.setType(expenseDto.getType());
        }
        if (expenseDto.getName() != null) {
            existingExpense.setName(expenseDto.getName());
        }
        if (expenseDto.getDateExpense() != null) {
            existingExpense.setDateExpense(expenseDto.getDateExpense());
        }
        if (expenseDto.getAmount() != null && expenseDto.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            existingExpense.setAmount(expenseDto.getAmount());
        }
        if (expenseDto.getFrequency() != null) {
            existingExpense.setFrequency(expenseDto.getFrequency());
        }
        expenseRepository.save(existingExpense);
    }
}
