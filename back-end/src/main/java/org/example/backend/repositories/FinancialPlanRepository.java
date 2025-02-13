package org.example.backend.repositories;

import org.example.backend.models.FinancialPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FinancialPlanRepository extends JpaRepository<FinancialPlan, Integer> {
    Optional<FinancialPlan> findByUser_Id(Integer userId);
}
