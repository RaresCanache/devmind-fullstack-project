package org.example.backend.mappers;

import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.DTOs.ResponseBankAccountDto;
import org.example.backend.models.BankAccount;
import org.example.backend.updateDTOs.BankAccountUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BankAccountMapper {
    BankAccount toModel(BankAccountDto bankAccountDto);

    ResponseBankAccountDto toDto(BankAccount bankAccount);

    void updateBankAccountFromDto(BankAccountUpdateDto bankAccountUpdateDto, @MappingTarget BankAccount bankAccount);
}
