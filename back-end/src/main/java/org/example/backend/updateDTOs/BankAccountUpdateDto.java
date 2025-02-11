package org.example.backend.updateDTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccountUpdateDto {
    @PositiveOrZero(message = "Can be either 0 or a positive balance")
    private BigDecimal balance;

    private boolean automatic;
}
