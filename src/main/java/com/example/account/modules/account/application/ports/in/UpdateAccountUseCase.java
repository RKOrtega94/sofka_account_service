package com.example.account.modules.account.application.ports.in;

import com.example.account.modules.account.application.dto.UpdateAccountRequestDTO;
import com.example.account.modules.account.domain.AccountModel;

public interface UpdateAccountUseCase {
    AccountModel update(UpdateAccountRequestDTO dto, Long id);
}
