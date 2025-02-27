package com.example.account.modules.account.application.service;

import com.example.account.core.clients.ClientResponseDTO;
import com.example.account.core.clients.UserClient;
import com.example.account.core.enums.MovementTypeEnum;
import com.example.account.core.utils.BaseResponse;
import com.example.account.modules.account.application.adapters.AccountRequestMapper;
import com.example.account.modules.account.application.dto.AccountResponseDTO;
import com.example.account.modules.account.application.dto.CreateAccountRequestDTO;
import com.example.account.modules.account.application.dto.UpdateAccountRequestDTO;
import com.example.account.modules.account.application.ports.in.*;
import com.example.account.modules.account.application.ports.out.AccountRepository;
import com.example.account.modules.account.domain.AccountModel;
import com.example.account.modules.movement.application.dto.MovementRequestDTO;
import com.example.account.modules.movement.application.service.MovementService;
import com.example.account.modules.movement.domain.MovementModel;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService implements CreateAccountUseCase, UpdateAccountUseCase, DeleteAccountUseCase, RetrieveAccountsUseCase, RetrieveAccountUseCase {
    private final AccountRequestMapper mapper;
    private final AccountRepository repository;
    private final UserClient userClient;
    private final MovementService movementService;

    @Override
    public AccountResponseDTO create(CreateAccountRequestDTO dto) {
        ClientResponseDTO client = getClient(dto.getClientId());
        AccountModel accountModel = mapper.toModel(dto);
        String accountNumber;
        do {
            accountNumber = generateAccountNumber();
        } while (repository.findByNumber(accountNumber).isPresent());
        accountModel.setNumber(accountNumber);
        AccountResponseDTO responseDTO = mapper.toResponse(repository.save(accountModel));
        responseDTO.setClient(client.getName());
        createFirstMovement(accountModel);
        return responseDTO;
    }


    @Override
    public AccountResponseDTO update(UpdateAccountRequestDTO dto, Long id) {
        AccountModel accountModel = repository.findById(id);
        ClientResponseDTO client = getClient(accountModel.getClientId());
        accountModel = mapper.toModel(dto);
        accountModel.setClientId(client.getId());
        AccountResponseDTO responseDTO = mapper.toResponse(repository.update(accountModel, id));
        responseDTO.setClient(client.getName());
        return responseDTO;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<AccountResponseDTO> retrieve(Map<String, Object> params) {
        Page<AccountModel> accounts = repository.findAll(params);
        return accounts.map(accountModel -> {
            AccountResponseDTO responseDTO = mapper.toResponse(accountModel);
            responseDTO.setClient(getClient(accountModel.getClientId()).getName());
            return responseDTO;
        });
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

    private ClientResponseDTO getClient(Long clientId) {
        try {
            ResponseEntity<BaseResponse<ClientResponseDTO>> response = userClient.get(clientId);
            if (response.getBody() == null || response.getBody().getData() == null)
                throw new IllegalArgumentException("Client not found");
            return response.getBody().getData();
        } catch (FeignException.FeignClientException e) {
            throw new IllegalArgumentException("Client not found");
        }
    }

    private void createFirstMovement(AccountModel accountModel) {
        MovementRequestDTO movementRequestDTO = new MovementRequestDTO();
        movementRequestDTO.setAmount(accountModel.getInitialBalance());
        movementRequestDTO.setAccountNumber(accountModel.getNumber());
        movementService.create(movementRequestDTO);
    }
}
