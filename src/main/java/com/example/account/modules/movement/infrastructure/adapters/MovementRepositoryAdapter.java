package com.example.account.modules.movement.infrastructure.adapters;

import com.example.account.modules.movement.application.ports.out.MovementRepository;
import com.example.account.modules.movement.domain.MovementModel;
import com.example.account.modules.movement.domain.MovementsReportSpecification;
import com.example.account.modules.movement.infrastructure.adapters.mapper.MovementMapper;
import com.example.account.modules.movement.infrastructure.persistence.JpaMovementRepository;
import com.example.account.modules.movement.infrastructure.persistence.MovementEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<MovementModel> generateReport(String dates) {
        Specification<MovementEntity> spec = MovementsReportSpecification.withDates(dates);
        return jpa.findAll(spec).stream().map(mapper::toModel).toList();
    }

    @Override
    public Optional<MovementModel> getLastMovement(Long accountId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<MovementEntity> movements = jpa.findAllByAccountId(accountId, sort);
        if (movements.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(mapper.toModel(movements.getFirst()));
        }
    }
}
