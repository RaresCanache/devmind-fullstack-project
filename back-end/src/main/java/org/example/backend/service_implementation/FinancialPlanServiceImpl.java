package org.example.backend.service_implementation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTOs.FinancialPlanDto;
import org.example.backend.exception_handlers.FinancialPlanNotFoundException;
import org.example.backend.exception_handlers.UserNotFoundException;
import org.example.backend.mappers.FinancialPlanMapper;
import org.example.backend.models.FinancialPlan;
import org.example.backend.models.User;
import org.example.backend.repositories.FinancialPlanRepository;
import org.example.backend.repositories.UserRepository;
import org.example.backend.service_interface.FinancialPlanService;
import org.example.backend.updateDTOs.FinancialPlanUpdateDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FinancialPlanServiceImpl implements FinancialPlanService {
    private final FinancialPlanRepository financialPlanRepository;

    private final UserRepository userRepository;

    private final FinancialPlanMapper financialPlanMapper;

    @Override
    public FinancialPlan createFinancialPlan(FinancialPlanDto financialPlanDto) {
        FinancialPlan financialPlan = financialPlanMapper.toModel(financialPlanDto);
        User user = userRepository.findById(financialPlanDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id: " + financialPlanDto.getUserId() + " not found"));
        financialPlan.setUser(user);

        return financialPlanRepository.save(financialPlan);
    }

    @Override
    public FinancialPlan getFinancialPlanByUserId(Integer userId) {
        return financialPlanRepository.findByUser_Id(userId)
                .orElseThrow(() -> new FinancialPlanNotFoundException("Financial plan for user with id: " + userId + " not found"));
    }

    @Override
    @Transactional
    public void replaceFinancialPlanByUserId(Integer userId, FinancialPlanUpdateDto financialPlanUpdateDto) {
        FinancialPlan existingFinancialPlan = financialPlanRepository.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));
        financialPlanMapper.updateFinancialPlanFromDto(financialPlanUpdateDto, existingFinancialPlan);

        financialPlanRepository.save(existingFinancialPlan);
    }
}
