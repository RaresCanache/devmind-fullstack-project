package org.example.backend.mappers;

import org.example.backend.DTOs.BankAccountDto;
import org.example.backend.models.BankAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {
    public BankAccount toModel(BankAccountDto bankAccountDto);

    public BankAccountDto toDto(BankAccount bankAccount);
}
