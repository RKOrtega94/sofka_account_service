package com.example.account.modules.account.application.dto;

import com.example.account.core.enums.AccountTypeEnum;
import lombok.Builder;
import lombok.With;

import java.math.BigDecimal;

@With
@Builder
public record AccountResponseDTO(Long id, String accountNumber, AccountTypeEnum type, BigDecimal initialBalance,
                                 boolean status, String client) {

}
