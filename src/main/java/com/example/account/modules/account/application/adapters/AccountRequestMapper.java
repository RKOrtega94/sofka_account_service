package com.example.account.modules.account.application.adapters;

import com.example.account.modules.account.application.dto.AccountResponseDTO;
import com.example.account.modules.account.application.dto.CreateAccountRequestDTO;
import com.example.account.modules.account.application.dto.UpdateAccountRequestDTO;
import com.example.account.modules.account.domain.AccountModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountRequestMapper {
    AccountRequestMapper INSTANCE = Mappers.getMapper(AccountRequestMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number", ignore = true)
    AccountModel toModel(CreateAccountRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "number", ignore = true)
    @Mapping(target = "initialBalance", ignore = true)
    @Mapping(target = "clientId", ignore = true)
    AccountModel toModel(UpdateAccountRequestDTO dto);

    @Mapping(target = "accountNumber", source = "number")
    @Mapping(target = "client", ignore = true)
    AccountResponseDTO toResponse(AccountModel model);
}
