package com.example.account.modules.movement.application.ports.out;

import com.example.account.modules.movement.domain.MovementModel;

import java.util.List;
import java.util.Optional;

public interface MovementRepository {
    MovementModel save(MovementModel movementModel);

    MovementModel update(MovementModel movementModel, Long id);

    MovementModel findById(Long id);

    void deleteById(Long id);

    List<MovementModel> findAllByAccountId(Long accountId);

    List<MovementModel> generateReport(String dates);

    Optional<MovementModel> getLastMovement(Long accountId);
}
