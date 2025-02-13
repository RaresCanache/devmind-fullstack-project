package org.example.backend.savings_logic;

import lombok.RequiredArgsConstructor;
import org.example.backend.exception_handlers.BankAccountNotFoundException;
import org.example.backend.exception_handlers.FinancialPlanNotFoundException;
import org.example.backend.models.BankAccount;
import org.example.backend.models.FinancialPlan;
import org.example.backend.repositories.BankAccountRepository;
import org.example.backend.repositories.ExpenseRepository;
import org.example.backend.repositories.FinancialPlanRepository;
import org.example.backend.service_interface.BankAccountService;
import org.example.backend.service_interface.ExpenseService;
import org.example.backend.service_interface.FinancialPlanService;
import org.example.backend.service_interface.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SavingsServiceImpl implements SavingsService {
    private final UserService userService;
    private final FinancialPlanService financialPlanService;
    private final ExpenseService expenseService;
    private final BankAccountService bankAccountService;

    //Here I used ChronoUnit for plans that span over more than 1 year
    private long noOfDaysFinancialPlan(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    //Used Lambda expressions for adding all expense amounts
    private BigDecimal totalExpenses(Integer userId) {
        return expenseService.getAllExpensesByUserId(userId)
                .stream()
                .map(expense -> expense.getAmount())
                .reduce(BigDecimal.ZERO, (total, amount) -> total.add(amount));
    }

    public List<Integer> computeAmountPerDayForUserIdAndBankAccountId(Integer userId, Integer bankAccountId) {
        userService.userExistsById(userId);

        FinancialPlan financialPlan = financialPlanService.getFinancialPlanById(userId);

        BankAccount bankAccount = bankAccountService.getBankAccountById(bankAccountId);

        List<Integer> amountRequiredPerDay = new ArrayList<>();
        LocalDate startDate = financialPlan.getStartDate();
        LocalDate endDate = financialPlan.getEndDate();

        long noDays = noOfDaysFinancialPlan(startDate, endDate);

        BigDecimal startingBalance = bankAccount.getBalance().subtract(totalExpenses(userId));

        System.out.println(startingBalance);
        return amountRequiredPerDay;
    }









}
