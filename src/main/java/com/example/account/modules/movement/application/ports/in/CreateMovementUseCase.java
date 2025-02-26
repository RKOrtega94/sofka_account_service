package com.example.account.modules.movement.application.ports.in;

import com.example.account.modules.movement.application.dto.MovementRequestDTO;
import com.example.account.modules.movement.domain.MovementModel;

public interface CreateMovementUseCase {
    MovementModel create(MovementRequestDTO dto);
}
