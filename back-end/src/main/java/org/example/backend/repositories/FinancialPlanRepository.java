package org.example.backend.repositories;

import org.example.backend.models.FinancialPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialPlanRepository extends JpaRepository<FinancialPlan, Integer> {
}
