package com.example.account.modules.movement.application.dto;

import com.example.account.core.enums.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementReportDTO {
    private Instant date;
    private String client;
    private String accountNumber;
    private AccountTypeEnum type;
    private BigDecimal initialBalance;
    private boolean status;
    private BigDecimal amount;
    private BigDecimal balance;
}
