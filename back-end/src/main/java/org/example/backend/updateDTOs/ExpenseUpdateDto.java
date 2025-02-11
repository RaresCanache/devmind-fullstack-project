package org.example.backend.updateDTOs;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.example.backend.models.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseUpdateDto {
    private Expense.TypeExpense type;

    @NotNull(message = "Should not be null")
    private String name;

    @FutureOrPresent(message = "Should be present or future date")
    private LocalDate dateExpense;

    @Positive(message = "Must be greater than 0")
    private BigDecimal amount;

    private Expense.FrequencyExpense frequency;
}
