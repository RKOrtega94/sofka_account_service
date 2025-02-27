package com.example.account.modules.account.infrastructure.api;

import com.example.account.core.utils.BaseResponse;
import com.example.account.modules.account.application.dto.AccountResponseDTO;
import com.example.account.modules.account.application.dto.CreateAccountRequestDTO;
import com.example.account.modules.account.application.dto.UpdateAccountRequestDTO;
import com.example.account.modules.account.application.ports.in.*;
import com.example.account.modules.account.domain.AccountModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuentas")
public class AccountController {
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;
    private final RetrieveAccountUseCase retrieveAccountUseCase;
    private final RetrieveAccountsUseCase retrieveAccountsUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<BaseResponse<AccountResponseDTO>> createAccount(@RequestBody @Valid CreateAccountRequestDTO dto) {
        return ResponseEntity.created(URI.create("/api/cuentas")).body(BaseResponse.<AccountResponseDTO>builder().message("Account created successfully").data(createAccountUseCase.create(dto)).build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<AccountResponseDTO>> updateAccount(@PathVariable Long id, @RequestBody @Valid UpdateAccountRequestDTO dto) {
        return ResponseEntity.ok().body(BaseResponse.<AccountResponseDTO>builder().message("Account updated successfully").data(updateAccountUseCase.update(dto, id)).build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        deleteAccountUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<AccountModel>> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok().body(BaseResponse.<AccountModel>builder().message("Account retrieved successfully").data(retrieveAccountUseCase.retrieve(id)).build());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<Page<AccountResponseDTO>>> getAccounts(Map<String, Object> params) {
        return ResponseEntity.ok().body(BaseResponse.<Page<AccountResponseDTO>>builder().message("Accounts retrieved successfully").data(retrieveAccountsUseCase.retrieve(params)).build());
    }
}
