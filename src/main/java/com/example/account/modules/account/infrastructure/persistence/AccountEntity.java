package com.example.account.modules.account.infrastructure.persistence;

import com.example.account.core.enums.AccountTypeEnum;
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
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum type;
    @Column(name = "balance")
    private BigDecimal initialBalance;
    private boolean status;
    @Column(name = "client_id")
    private Long clientId;

    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MovementEntity> movements;
}
