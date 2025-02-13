package org.example.backend.mappers;

import org.example.backend.DTOs.FinancialPlanDto;
import org.example.backend.models.FinancialPlan;
import org.example.backend.updateDTOs.FinancialPlanUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FinancialPlanMapper {
    FinancialPlan toModel(FinancialPlanDto financialPlanDto);

    FinancialPlanDto toDto(FinancialPlan financialPlan);

    void updateFinancialPlanFromDto(FinancialPlanUpdateDto financialPlanUpdateDto, @MappingTarget FinancialPlan financialPlan);
}
