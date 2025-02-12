package org.example.backend.mappers;

import org.example.backend.DTOs.TransactionDto;
import org.example.backend.models.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper {
    Transaction toModel(TransactionDto transactionDto);

    TransactionDto toDto(Transaction transaction);
}
