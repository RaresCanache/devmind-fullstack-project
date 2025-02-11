package org.example.backend.mappers;

import org.example.backend.DTOs.ExpenseDto;
import org.example.backend.models.Expense;
import org.example.backend.updateDTOs.ExpenseUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExpenseMapper {
    @Mapping(target = "name", defaultValue = "Expense")
    Expense toModel(ExpenseDto expenseDto);

    ExpenseDto toDto(Expense expense);

    void updateExpenseFromDto(ExpenseUpdateDto expenseUpdateDto, @MappingTarget Expense expense);

}
