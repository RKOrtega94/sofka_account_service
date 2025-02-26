package com.example.account.modules.movement.application.ports.in;

import com.example.account.modules.movement.domain.MovementModel;

import java.util.List;

public interface RetrieveMovementsUseCase {
    List<MovementModel> retrieveAll(Long accountId);
}
