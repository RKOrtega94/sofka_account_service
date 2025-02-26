package com.example.account.modules.movement.infrastructure.adapters;

import com.example.account.modules.movement.application.ports.out.MovementRepository;
import com.example.account.modules.movement.domain.MovementModel;
import com.example.account.modules.movement.infrastructure.adapters.mapper.MovementMapper;
import com.example.account.modules.movement.infrastructure.persistence.JpaMovementRepository;
import com.example.account.modules.movement.infrastructure.persistence.MovementEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MovementRepositoryAdapter implements MovementRepository {
    private final MovementMapper mapper;
    private final JpaMovementRepository jpa;

    @Override
    public MovementModel save(MovementModel movementModel) {
        MovementEntity entity = mapper.toEntity(movementModel);
        return mapper.toModel(jpa.save(entity));
    }

    @Override
    public MovementModel update(MovementModel movementModel, Long id) {
        MovementEntity entity = jpa.findById(id).orElseThrow(EntityNotFoundException::new);
        entity.setId(id);
        mapper.update(movementModel, entity);
        return mapper.toModel(jpa.save(entity));
    }

    @Override
    public MovementModel findById(Long id) {
        return jpa.findById(id).map(mapper::toModel).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        jpa.findById(id).orElseThrow(EntityNotFoundException::new);
        jpa.deleteById(id);
    }

    @Override
    public List<MovementModel> findAllByAccountId(Long accountId) {
        return jpa.findAllByAccountId(accountId).stream().map(mapper::toModel).toList();
    }
}
