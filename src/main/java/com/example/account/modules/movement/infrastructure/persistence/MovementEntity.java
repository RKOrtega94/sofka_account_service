package com.example.account.modules.movement.infrastructure.persistence;

import com.example.account.core.MovementTypeEnum;
import com.example.account.modules.account.infrastructure.persistence.AccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant date;
    @Enumerated(EnumType.STRING)
    private MovementTypeEnum type;
    private BigDecimal amount;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;
}
