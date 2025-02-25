package org.example.backend.response_DTOs;

import lombok.Data;
import org.example.backend.models.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseResponseDto {
    private Integer id;
    private Expense.TypeExpense type;
    private String name;
    private LocalDate dateExpense;
    private BigDecimal amount;
    private Expense.FrequencyExpense frequency;

}
