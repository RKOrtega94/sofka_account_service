package com.example.account.modules.account.application.ports.in;

import com.example.account.modules.account.application.dto.AccountResponseDTO;
import com.example.account.modules.account.application.dto.CreateAccountRequestDTO;
import com.example.account.modules.account.domain.AccountModel;

public interface CreateAccountUseCase {
    AccountResponseDTO create(CreateAccountRequestDTO dto);
}
