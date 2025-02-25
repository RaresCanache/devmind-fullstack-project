package org.example.backend.response_DTOs;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccountResponseDto {
    private Integer id;
    private String bankName;
    private BigDecimal balance;
    private String currency;
}
