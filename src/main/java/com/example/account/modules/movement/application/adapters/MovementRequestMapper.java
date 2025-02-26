package com.example.account.modules.movement.application.adapters;

import com.example.account.modules.account.infrastructure.adapters.mapper.AccountMapper;
import com.example.account.modules.movement.application.dto.MovementRequestDTO;
import com.example.account.modules.movement.application.ports.in.CreateMovementUseCase;
import com.example.account.modules.movement.domain.MovementModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovementRequestMapper {
    MovementRequestMapper INSTANCE = Mappers.getMapper(MovementRequestMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "account", ignore = true)
    @Mapping(target = "type", ignore = true)
    MovementModel toModel(MovementRequestDTO dto);
}
