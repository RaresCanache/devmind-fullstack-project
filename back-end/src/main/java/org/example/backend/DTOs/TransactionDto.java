package org.example.backend.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionDto {
    @NotNull(message = "Should not be null")
    private Integer bankAccountId;

    @NotNull(message = "Should not be null")
    private LocalDate transactionDate;

    @NotBlank(message = "Should not be null or blank")
    private String vendor;

    @Positive(message = "Must be greater than 0")
    @NotNull(message = "Should not be null")
    private BigDecimal amount;
}
