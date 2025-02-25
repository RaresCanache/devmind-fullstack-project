package org.example.backend.mappers;

import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.models.Expense;
import org.example.backend.response_DTOs.ExpenseResponseDto;
import org.example.backend.updateDTOs.ExpenseUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExpenseMapper {
    Expense toModel(ExpenseDto expenseDto);

    ExpenseResponseDto toDto(Expense expense);

    void updateExpenseFromDto(ExpenseUpdateDto expenseUpdateDto, @MappingTarget Expense expense);

}
