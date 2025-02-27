package com.example.account.modules.account.infrastructure.api;

import com.example.account.core.utils.http.*;
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
        BaseResponse<AccountResponseDTO> response = new SuccessResponse<>(createAccountUseCase.create(dto), "Account created successfully", HttpStatus.CREATED);
        return ResponseEntity.created(URI.create("/api/cuentas")).body(response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<AccountResponseDTO>> updateAccount(@PathVariable Long id, @RequestBody @Valid UpdateAccountRequestDTO dto) {
        BaseResponse<AccountResponseDTO> response = new SuccessResponse<>(updateAccountUseCase.update(dto, id), "Account updated successfully", HttpStatus.OK);
        return ResponseEntity.ok().body(response);
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
        BaseResponse<AccountModel> response = new SuccessResponse<>(retrieveAccountUseCase.retrieve(id), "Account retrieved successfully", HttpStatus.OK);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<Page<AccountResponseDTO>>> getAccounts(Map<String, Object> params) {
        BaseResponse<Page<AccountResponseDTO>> response = new SuccessResponse<>(retrieveAccountsUseCase.retrieve(params), "Accounts retrieved successfully", HttpStatus.OK);
        return ResponseEntity.ok().body(response);
    }
}
