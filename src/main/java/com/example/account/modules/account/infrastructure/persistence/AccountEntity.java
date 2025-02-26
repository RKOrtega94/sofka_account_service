package com.example.account.modules.account.infrastructure.persistence;

import com.example.account.core.AccountTypeEnum;
import com.example.account.modules.movement.infrastructure.persistence.MovementEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum type;
    @Column(name = "initial_balance")
    private BigDecimal initialBalance;
    private boolean status;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementEntity> movements;
}
