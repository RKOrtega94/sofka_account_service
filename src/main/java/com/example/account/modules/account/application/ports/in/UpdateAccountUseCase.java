package com.example.account.modules.account.application.ports.in;

import com.example.account.modules.account.application.dto.AccountResponseDTO;
import com.example.account.modules.account.application.dto.UpdateAccountRequestDTO;
import com.example.account.modules.account.domain.AccountModel;

public interface UpdateAccountUseCase {
    AccountResponseDTO update(UpdateAccountRequestDTO dto, Long id);
}
