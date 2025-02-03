package org.example.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    @JsonBackReference
    private BankAccount bankAccount;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
