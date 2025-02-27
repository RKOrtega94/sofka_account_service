package com.example.account.modules.account.application.dto;

import com.example.account.core.enums.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {
    private Long id;
    private String accountNumber;
    private AccountTypeEnum type;
    private BigDecimal initialBalance;
    private boolean status;
    private String client;
}
