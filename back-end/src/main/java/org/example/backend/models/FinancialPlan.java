package org.example.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "financial_plan")
@Getter
@Setter
@ToString
public class FinancialPlan {
    public enum TypeFinancialPlan {
        PERCENT, FIXED;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeFinancialPlan type;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "savings", precision = 10, scale = 2)
    private BigDecimal savings;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
