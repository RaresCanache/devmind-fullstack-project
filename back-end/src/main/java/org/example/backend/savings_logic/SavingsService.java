package org.example.backend.savings_logic;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SavingsService {
    public List<BigDecimal> computeAmountPerDayForUserIdAndBankAccountId(Integer userId, Integer bankAccountId);
}
