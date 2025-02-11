package org.example.backend.DTOs;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccountDto {
    @NotNull(message = "Should not be null")
    private Integer userId;

    @NotBlank(message = "Should not be null, but it can have a default value")
    private String bankName;

    @NotBlank(message = "Should not be null or blank")
    private String iban;

    @NotBlank(message = "Should not be null blank")
    @Size(min = 3, max = 3, message = "Currency must be a 3 character code")
    private String currency;

    @PositiveOrZero(message = "Can be either 0 or a positive balance")
    @NotNull(message = "Should not e null")
    private BigDecimal balance;

    @AssertFalse(message = "It must be a manual account at first. It can be changed afterwards")
    private boolean is_automatic;
}
