package com.example.account.modules.account.application.ports.in;

import com.example.account.modules.account.domain.AccountModel;

public interface RetrieveAccountUseCase {
    AccountModel retrieve(Long id);
}
