package com.example.account.modules.account.application.dto;

import com.example.account.core.AccountTypeEnum;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {
    @NotNull
    private AccountTypeEnum type;
    @NotNull
    @Digits(integer = 9, fraction = 6)
    private Double initialBalance;
    @Builder.Default
    private boolean status = true;
}
