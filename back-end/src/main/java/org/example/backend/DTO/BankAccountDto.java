package org.example.backend.DTO;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BankAccountDto {
    @NotNull(message = "Should not be null")
    private Integer userId;

    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    private String bankName;

    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    private String iban;

    @NotNull(message = "Should not be null")
    @NotBlank(message = "Should not be blank")
    @Size(min = 3, max = 3, message = "Currency must be a 3 character code")
    private String currency;

    @AssertFalse(message = "It must be a manual account at first. It can be changed afterwards")
    private boolean is_automatic;
}
