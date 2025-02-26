package com.example.account.modules.account.application.ports.out;

import com.example.account.modules.account.domain.AccountModel;

import java.util.List;
import java.util.Map;

public interface AccountRepository {
    AccountModel save(AccountModel accountModel);

    AccountModel update(AccountModel accountModel, Long id);

    AccountModel findById(Long id);

    List<AccountModel> findAll(Map<String, Object> params);

    void deleteById(Long id);
}
