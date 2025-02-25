package org.example.backend.response_DTOs;

import lombok.Data;
import org.example.backend.models.FinancialPlan;

import java.util.List;

@Data
public class UserResponseDto {
    private Integer id;
    private String username;
    private String email;
    private String fullName;
    private String avatarUrl;
    private FinancialPlan financialPlan;
    private boolean premium;

}
