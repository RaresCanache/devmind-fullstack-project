package org.example.backend.savings_logic;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;
import java.util.List;

public interface SavingsService {
    public List<Integer> computeAmountPerDayForUserIdAndBankAccountId(Integer userId, Integer bankAccountId);
}
