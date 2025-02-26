package com.example.account.modules.movement.application.ports.in;

import com.example.account.modules.movement.domain.MovementModel;

public interface RetrieveMovementUseCase {
    MovementModel retrieve(Long id);
}
