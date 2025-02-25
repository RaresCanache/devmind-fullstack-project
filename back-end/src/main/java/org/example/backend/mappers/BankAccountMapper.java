package org.example.backend.mappers;

import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.models.BankAccount;
import org.example.backend.response_DTOs.BankAccountResponseDto;
import org.example.backend.updateDTOs.BankAccountUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BankAccountMapper {
    BankAccount toModel(BankAccountDto bankAccountDto);

    BankAccountResponseDto toDto(BankAccount bankAccount);

    void updateBankAccountFromDto(BankAccountUpdateDto bankAccountUpdateDto, @MappingTarget BankAccount bankAccount);
}
