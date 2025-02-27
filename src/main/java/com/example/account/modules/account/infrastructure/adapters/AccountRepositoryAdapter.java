package com.example.account.modules.account.infrastructure.adapters;

import com.example.account.core.utils.BuildPaginationRequest;
import com.example.account.modules.account.application.ports.out.AccountRepository;
import com.example.account.modules.account.domain.AccountModel;
import com.example.account.modules.account.infrastructure.adapters.mapper.AccountMapper;
import com.example.account.modules.account.infrastructure.persistence.AccountEntity;
import com.example.account.modules.account.infrastructure.persistence.JpaAccountRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryAdapter implements AccountRepository {
    private final AccountMapper mapper;
    private final JpaAccountRepository jpa;

    @Override
    public AccountModel save(AccountModel accountModel) {
        AccountEntity entity = mapper.toEntity(accountModel);
        return mapper.toModel(jpa.save(entity));
    }

    @Override
    public AccountModel update(AccountModel accountModel, Long id) {
       /* AccountEntity entity = jpa.findById(id).orElseThrow(EntityNotFoundException::new);
        mapper.update(accountModel, entity);
        entity.setId(id);
        if (accountModel.getInitialBalance() != null) entity.setBalance(accountModel.getBalance());
        return mapper.toModel(jpa.save(entity));*/
        return null;
    }

    @Override
    public AccountModel findById(Long id) {
        return mapper.toModel(jpa.findById(id).orElseThrow(EntityExistsException::new));
    }

    @Override
    public Page<AccountModel> findAll(Map<String, Object> params) {
        PageRequest pageRequest = BuildPaginationRequest.build(params);
        return jpa.findAll(pageRequest).map(mapper::toModel);
    }

    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public Optional<AccountModel> findByNumber(String number) {
        return jpa.findByNumber(number).map(mapper::toModel);
    }
}
