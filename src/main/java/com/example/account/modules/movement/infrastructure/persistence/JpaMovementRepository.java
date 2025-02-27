package com.example.account.modules.movement.infrastructure.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaMovementRepository extends JpaRepository<MovementEntity, Long>, JpaSpecificationExecutor<MovementEntity> {
    List<MovementEntity> findAllByAccountId(Long accountId);

    List<MovementEntity> findAllByAccountId(Long accountId, Sort sort);
}
