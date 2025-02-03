package org.example.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "bank_accounts")
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "iban")
    private String iban;

    //Pentru financial values
    @Column(name = "balance", precision = 10, scale = 2)
    private BigDecimal balance;

    @Column(name = "currency")
    private String currency;

    @Column(name = "is_automatic")
    private boolean hasAutomaticTransfer;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private Set<Transaction> transactions;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
