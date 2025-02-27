package com.example.account.modules.account.application.dto;

import com.example.account.core.enums.AccountTypeEnum;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateAccountRequestDTO(@NotNull AccountTypeEnum type,
                                      @Min(0) @NotNull @Digits(integer = 9, fraction = 6) Double initialBalance,
                                      boolean status, @NotNull Long clientId) {

}
