package org.example.backend.service_implementation;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.exception_handlers.ExpenseNotFoundException;
import org.example.backend.exception_handlers.UserNotFoundException;
import org.example.backend.mappers.ExpenseMapper;
import org.example.backend.models.Expense;
import org.example.backend.models.User;
import org.example.backend.repositories.ExpenseRepository;
import org.example.backend.repositories.UserRepository;
import org.example.backend.service_interface.ExpenseService;
import org.example.backend.updateDTOs.ExpenseUpdateDto;
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
    public Expense getExpenseById(Integer expenseId) {
        return expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenseNotFoundException("Expense with id: " + expenseId + " not found"));
    }

    @Override
    public List<Expense> getAllExpensesByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }
        return expenseRepository.findAllByUser_Id(userId);
    }

    @Override
    public Expense createExpense(ExpenseDto expenseDto) {
        Expense expense = expenseMapper.toModel(expenseDto);
        User user = userRepository.findById(expenseDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id: " + expenseDto.getUserId() + " not found"));
        expense.setUser(user);

        return expenseRepository.save(expense);
    }

    @Override
    @Transactional
    public void updateExpenseById(Integer expenseId, ExpenseUpdateDto expenseUpdateDto) {
        Expense existingExpense = expenseRepository.findById(expenseId).orElseThrow(() -> new ExpenseNotFoundException("Expense with id: " + expenseId + " not found"));
        expenseMapper.updateExpenseFromDto(expenseUpdateDto, existingExpense);

        expenseRepository.save(existingExpense);
    }

    @Override
    public void deleteExpenseById(Integer expenseId) {
        if (!expenseRepository.existsById(expenseId)) {
            throw new ExpenseNotFoundException("Expense with id: " + expenseId + " not found");
        }
        expenseRepository.deleteById(expenseId);
    }

    @Override
    @Transactional
    public void deleteAllExpensesByUserId(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User with id: " + userId + " not found");
        }
        expenseRepository.deleteAllByUser_Id(userId);
    }
}
