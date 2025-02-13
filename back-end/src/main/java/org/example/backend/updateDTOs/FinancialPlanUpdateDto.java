package org.example.backend.updateDTOs;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.example.backend.models.FinancialPlan;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class FinancialPlanUpdateDto {
    private FinancialPlan.TypeFinancialPlan type;

    @Positive(message = "Must be greater than 0")
    private BigDecimal savings;

    @FutureOrPresent(message = "Must be a present or future date")
    private LocalDate startDate;

    @Future(message = "Must be a present or future date")
    private LocalDate endDate;
}
