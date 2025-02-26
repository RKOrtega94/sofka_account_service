package com.example.account.modules.movement.application.ports.in;

import com.example.account.modules.movement.application.dto.MovementRequestDTO;
import com.example.account.modules.movement.domain.MovementModel;

public interface UpdateMovementUseCase {
    MovementModel update(MovementRequestDTO dto, Long id);
}
