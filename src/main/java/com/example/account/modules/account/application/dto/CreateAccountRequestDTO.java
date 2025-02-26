package com.example.account.modules.account.application.dto;

import com.example.account.core.enums.AccountTypeEnum;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestDTO {
    @NotNull
    private AccountTypeEnum type;
    @NotNull
    @Digits(integer = 9, fraction = 6)
    private Double initialBalance;
    @Builder.Default
    private boolean status = true;
}
