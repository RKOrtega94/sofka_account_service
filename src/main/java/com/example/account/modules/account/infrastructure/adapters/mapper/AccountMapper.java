package com.example.account.modules.account.infrastructure.adapters.mapper;

import com.example.account.modules.account.domain.AccountModel;
import com.example.account.modules.account.infrastructure.persistence.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "movements", ignore = true)
    AccountEntity toEntity(AccountModel model);

    AccountModel toModel(AccountEntity entity);

    @Mapping(target = "number", ignore = true)
    @Mapping(target = "movements", ignore = true)
    @Mapping(target = "initialBalance", ignore = true)
    void update(AccountModel model, @MappingTarget AccountEntity entity);
}
