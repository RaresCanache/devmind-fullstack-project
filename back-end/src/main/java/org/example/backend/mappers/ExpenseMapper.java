package org.example.backend.mappers;

import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.models.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toModel(ExpenseDto expenseDto);

    ExpenseDto toDto(Expense expense);

    void updateExpenseFromDto(@MappingTarget Expense existingExpense, ExpenseDto expenseDto);
}
