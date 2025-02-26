package com.example.account.modules.account.application.ports.out;

import com.example.account.modules.account.domain.AccountModel;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.Optional;

public interface AccountRepository {
    AccountModel save(AccountModel accountModel);

    AccountModel update(AccountModel accountModel, Long id);

    AccountModel findById(Long id);

    Page<AccountModel> findAll(Map<String, Object> params);

    void deleteById(Long id);

    Optional<AccountModel> findByNumber(String number);
}
