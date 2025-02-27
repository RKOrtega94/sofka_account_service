package com.example.account.modules.movement.application.dto;

import com.example.account.core.enums.AccountTypeEnum;
import com.example.account.core.enums.MovementTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovementResponseDTO {
    private Long id;
    private String accountNumber;
    private AccountTypeEnum type;
    private BigDecimal balance;
    private boolean status;
    private String movement;
}
