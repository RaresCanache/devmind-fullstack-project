package org.example.backend.exception_handlers;

public class FinancialPlanNotFoundException extends RuntimeException {
    public FinancialPlanNotFoundException(String message) {
        super(message);
    }
}
