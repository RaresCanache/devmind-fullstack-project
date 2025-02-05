package org.example.backend.DTOs;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.backend.models.FinancialPlan;

import java.time.LocalDate;

@Data
public class FinancialPlanDto {
    @NotNull(message = "Should not be null")
    private Integer userId;

    @NotNull(message = "Should not be null")
    private FinancialPlan.TypeFinancialPlan type;

    @NotNull(message = "Should not be null")
    @FutureOrPresent(message = "Must be a present or future date")
    private LocalDate startDate;

    @NotNull(message = "Should not be null")
    @Future(message = "Must be a present or future date")
    private LocalDate endDate;
}
