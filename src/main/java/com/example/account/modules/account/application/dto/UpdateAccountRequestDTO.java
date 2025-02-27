package com.example.account.modules.account.application.dto;

import com.example.account.core.enums.AccountTypeEnum;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public record UpdateAccountRequestDTO(@NotNull AccountTypeEnum type, boolean status) {

}
