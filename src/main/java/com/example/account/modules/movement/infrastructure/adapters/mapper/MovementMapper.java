package com.example.account.modules.movement.infrastructure.adapters.mapper;

import com.example.account.modules.account.infrastructure.adapters.mapper.AccountMapper;
import com.example.account.modules.movement.domain.MovementModel;
import com.example.account.modules.movement.infrastructure.persistence.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = AccountMapper.class)
public interface MovementMapper {
    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);

    MovementEntity toEntity(MovementModel model);

    MovementModel toModel(MovementEntity entity);

    void update(MovementModel model, @MappingTarget MovementEntity entity);
}
