package com.example.account.modules.account.domain;

import com.example.account.core.enums.AccountTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountModel {
    private Long id;
    private String number;
    private AccountTypeEnum type;
    private BigDecimal initialBalance;
    private boolean status;
    private Long clientId;
}
