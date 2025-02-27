package com.example.account.modules.account.application.service;

import com.example.account.core.clients.ClientResponseDTO;
import com.example.account.core.clients.UserClient;
import com.example.account.core.utils.http.BaseResponse;
import com.example.account.core.utils.http.ErrorResponse;
import com.example.account.core.utils.http.SuccessResponse;
import com.example.account.modules.account.application.adapters.AccountRequestMapper;
import com.example.account.modules.account.application.dto.AccountResponseDTO;
import com.example.account.modules.account.application.dto.CreateAccountRequestDTO;
import com.example.account.modules.account.application.dto.UpdateAccountRequestDTO;
import com.example.account.modules.account.application.ports.in.*;
import com.example.account.modules.account.application.ports.out.AccountRepository;
import com.example.account.modules.account.domain.AccountModel;
import com.example.account.modules.movement.application.dto.MovementRequestDTO;
import com.example.account.modules.movement.application.service.MovementService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService implements CreateAccountUseCase, UpdateAccountUseCase, DeleteAccountUseCase, RetrieveAccountsUseCase, RetrieveAccountUseCase {
    private final AccountRequestMapper mapper;
    private final AccountRepository repository;
    private final UserClient userClient;
    private final MovementService movementService;

    @Override
    public AccountResponseDTO create(CreateAccountRequestDTO dto) {
        ClientResponseDTO client = getClient(dto.clientId());
        AccountModel accountModel = mapper.toModel(dto);
        String accountNumber;
        do {
            accountNumber = generateAccountNumber();
        } while (repository.findByNumber(accountNumber).isPresent());
        accountModel.setNumber(accountNumber);
        AccountResponseDTO responseDTO = mapper.toResponse(repository.save(accountModel));
        createFirstMovement(accountModel);
        return responseDTO;
    }


    @Override
    public AccountResponseDTO update(UpdateAccountRequestDTO dto, Long id) {
        AccountModel accountModel = repository.findById(id);
        ClientResponseDTO client = getClient(accountModel.getClientId());
        accountModel = mapper.toModel(dto);
        accountModel.setClientId(client.getId());
        return mapper.toResponse(repository.update(accountModel, id));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<AccountResponseDTO> retrieve(Map<String, Object> params) {
        Page<AccountModel> accounts = repository.findAll(params);
        return accounts.map(mapper::toResponse);
    }

    @Override
    public AccountModel retrieve(Long id) {
        return repository.findById(id);
    }

    private String generateAccountNumber() {
        String base = "1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = (int) (base.length() * Math.random());
            sb.append(base.charAt(index));
        }
        return sb.toString();
    }

    private ClientResponseDTO getClient(Long clientId) throws FeignException.FeignClientException {
        ResponseEntity<BaseResponse<ClientResponseDTO>> response = userClient.get(clientId);
        if (response.getStatusCode().is2xxSuccessful()) {
            SuccessResponse<ClientResponseDTO> successResponse = (SuccessResponse<ClientResponseDTO>) response.getBody();
            return Objects.requireNonNull(successResponse).data();
        } else {
            ErrorResponse<ClientResponseDTO> errorResponse = new ErrorResponse<>("Error", HttpStatus.BAD_REQUEST, List.of());
            throw new IllegalArgumentException(errorResponse.message());
        }
    }

    private void createFirstMovement(AccountModel accountModel) {
        MovementRequestDTO movementRequestDTO = new MovementRequestDTO();
        movementRequestDTO.setAmount(accountModel.getInitialBalance());
        movementRequestDTO.setAccountNumber(accountModel.getNumber());
        movementService.create(movementRequestDTO);
    }
}
