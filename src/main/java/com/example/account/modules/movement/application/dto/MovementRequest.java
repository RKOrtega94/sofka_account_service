package com.example.account.modules.movement.application.dto;

import com.example.account.core.MovementTypeEnum;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovementRequest {
    @NotNull
    private Long accountId;
    @NotNull
    private MovementTypeEnum type;
    @NotNull
    @Min(0)
    @Digits(integer = 9, fraction = 6)
    private Double amount;
}
