package org.example.backend.savings_logic;

import lombok.RequiredArgsConstructor;
import org.example.backend.models.BankAccount;
import org.example.backend.models.FinancialPlan;
import org.example.backend.response_DTOs.BankAccountResponseDto;
import org.example.backend.service_interface.BankAccountService;
import org.example.backend.service_interface.ExpenseService;
import org.example.backend.service_interface.FinancialPlanService;
import org.example.backend.service_interface.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
    private long noDaysFinancialPlan(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    //Used Lambda expressions for summing all expense amounts using a reduce function
    private BigDecimal totalExpenses(Integer userId) {
        return expenseService.getAllExpensesByUserId(userId)
                .stream()
                .map(expense -> expense.getAmount())
                .reduce(BigDecimal.ZERO, (total, amount) -> total.add(amount));
    }

    private List<Integer> amountRequiredPerEachDay(BigDecimal balance, BigDecimal toSubtract, long noDays) {
        List<Integer> amountRequiredPerEachDay = new ArrayList<>();

        for (int i = 0; i < noDays; i++) {
            balance = balance.subtract(toSubtract);
            /*
            Used setScale to round the results half up, otherwise I would get negative values because of
            startingBalanceDivByNoDays function being rounded up as well
            */
            amountRequiredPerEachDay.add(balance.setScale(0, RoundingMode.HALF_UP).intValueExact());
        }

        return amountRequiredPerEachDay;
    }

    public List<Integer> computeAmountPerDayForUserIdAndBankAccountId(Integer userId, Integer bankAccountId) {
        userService.userExistsById(userId);

        FinancialPlan financialPlan = financialPlanService.getFinancialPlanById(userId);

        BankAccountResponseDto bankAccount = bankAccountService.getBankAccountById(bankAccountId);

        LocalDate startDate = financialPlan.getStartDate();
        LocalDate endDate = financialPlan.getEndDate();

        long noDays = noDaysFinancialPlan(startDate, endDate);
        /*
        Here we compute the starting balance that remains by subtracting the total amount of all expenses
        that occur in the period set for the financial plan and subtracting the amount to be saved as well
        */
        BigDecimal startingBalance = bankAccount.getBalance().subtract(totalExpenses(userId))
                .subtract(financialPlan.getSavings());

        //Used rounding half up in case the division quotient has an infinite number of decimals
        BigDecimal startingBalanceDivByNoDays = startingBalance.divide(BigDecimal.valueOf(noDays), RoundingMode.HALF_UP);
        return amountRequiredPerEachDay(startingBalance, startingBalanceDivByNoDays, noDays);
    }
}
