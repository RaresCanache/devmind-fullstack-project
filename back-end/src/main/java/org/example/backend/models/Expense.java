package org.example.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jdk.jfr.Frequency;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "expenses")
@Data
public class Expense {
    public enum TypeExpense {
        GROCERIES, TRANSPORT, UTILITIES, SUBSCRIPTIONS, DEBT, OTHER;
    }

    public enum FrequencyExpense {
        DAILY, WEEKLY, MONTHLY;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeExpense type;

    @Column(name = "name")
    private String name;

    @Column(name = "date_expense")
    private LocalDate dateExpense;

    //Recomandat pt lucrul cu financial values
    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency")
    private FrequencyExpense frequency;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
