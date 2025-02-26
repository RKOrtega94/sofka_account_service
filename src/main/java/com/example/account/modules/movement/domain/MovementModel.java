package com.example.account.modules.movement.domain;

import com.example.account.core.enums.MovementTypeEnum;
import com.example.account.modules.account.domain.AccountModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementModel {
    private Long id;
    private Instant date;
    private MovementTypeEnum type;
    private BigDecimal amount;
    private BigDecimal balance;
    private AccountModel account;
}
