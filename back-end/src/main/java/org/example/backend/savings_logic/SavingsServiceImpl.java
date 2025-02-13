package org.example.backend.savings_logic;

import lombok.RequiredArgsConstructor;
import org.example.backend.exception_handlers.BankAccountNotFoundException;
import org.example.backend.exception_handlers.FinancialPlanNotFoundException;
import org.example.backend.exception_handlers.UserNotFoundException;
import org.example.backend.models.BankAccount;
import org.example.backend.models.Expense;
import org.example.backend.models.FinancialPlan;
import org.example.backend.repositories.BankAccountRepository;
import org.example.backend.repositories.ExpenseRepository;
import org.example.backend.repositories.FinancialPlanRepository;
import org.example.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingsServiceImpl implements SavingsService {
    private final UserRepository userRepository;
    private final FinancialPlanRepository financialPlanRepository;
    private final ExpenseRepository expenseRepository;
    private final BankAccountRepository bankAccountRepository;

    //Here I used ChronoUnit for plans that span over more than 1 year
    private long noOfDaysFinancialPlan(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    //Used Lambda expressions for adding all expense amounts
    private BigDecimal totalExpenses(Integer userId) {
        return expenseRepository.findAllByUser_Id(userId)
                .stream()
                .map(expense -> expense.getAmount())
                .reduce(BigDecimal.ZERO, (total, amount) -> total.add(amount));
    }

    public List<Integer> computeAmountPerDayForUserIdAndBankAccountId(Integer userId, Integer bankAccountId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User with id: " + userId + " not found");
        }
        FinancialPlan financialPlan = financialPlanRepository.findByUser_Id(userId)
                .orElseThrow(() -> new FinancialPlanNotFoundException("Financial plan not existent"));

        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account with id:" + bankAccountId + " not found"));

        List<Integer> amountRequiredPerDay = new ArrayList<>();
        LocalDate startDate = financialPlan.getStartDate();
        LocalDate endDate = financialPlan.getEndDate();

        long noDays = noOfDaysFinancialPlan(startDate, endDate);

        BigDecimal startingBalance = bankAccount.getBalance().subtract(totalExpenses(userId));


        return amountRequiredPerDay;
    }









}
