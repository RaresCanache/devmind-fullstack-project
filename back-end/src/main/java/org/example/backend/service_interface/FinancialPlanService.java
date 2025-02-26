package org.example.backend.service_interface;

import org.example.backend.DTOs.FinancialPlanDto;
import org.example.backend.models.FinancialPlan;
import org.example.backend.updateDTOs.FinancialPlanUpdateDto;

import java.util.List;

public interface FinancialPlanService {
    FinancialPlan getFinancialPlanByUserId(Integer financialPlanId);

    FinancialPlan createFinancialPlan(FinancialPlanDto financialPlanDto);

    void replaceFinancialPlanByUserId(Integer userId, FinancialPlanUpdateDto financialPlanUpdateDto);
}
