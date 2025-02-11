package org.example.backend.DTOs;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.example.backend.models.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseDto {
    @NotNull(message = "Should not be null")
    private Integer userId;

    @NotNull(message = "Should not be null")
    private Expense.TypeExpense type;

    private String name = "Expense";

    @FutureOrPresent(message = "Should be present or future date")
    @NotNull(message = "Should not be null")
    private LocalDate dateExpense;

    @Positive(message = "Must be greater than 0")
    @NotNull(message = "Should not be null")
    private BigDecimal amount;

    @NotNull(message = "Should not be null")
    private Expense.FrequencyExpense frequency;
}
