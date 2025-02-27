package com.example.account.modules.account.application.ports.in;


import com.example.account.modules.account.application.dto.AccountResponseDTO;
import com.example.account.modules.account.domain.AccountModel;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface RetrieveAccountsUseCase {
    Page<AccountResponseDTO> retrieve(Map<String, Object> params);
}
