package com.example.account.modules.account.application.ports.in;

import com.example.account.modules.account.application.dto.CreateAccountRequestDTO;
import com.example.account.modules.account.domain.AccountModel;

public interface CreateAccountUseCase {
    AccountModel create(CreateAccountRequestDTO dto);
}
