package org.example.backend.mappers;

import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.models.Expense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {
    Expense toModel(ExpenseDto expenseDto);

    ExpenseDto toDto(Expense expense);
}
