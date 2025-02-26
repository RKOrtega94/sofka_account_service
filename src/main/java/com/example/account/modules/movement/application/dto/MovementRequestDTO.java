package com.example.account.modules.movement.application.dto;

import com.example.account.core.enums.MovementTypeEnum;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementRequestDTO {
    @NotNull
    private String accountNumber;
    @NotNull
    @Digits(integer = 9, fraction = 6)
    @DecimalMin(value = "-999999999", inclusive = false)
    @DecimalMax(value = "999999999", inclusive = false)
    private BigDecimal amount;
}
