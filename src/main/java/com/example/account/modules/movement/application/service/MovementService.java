package com.example.account.modules.movement.application.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.account.core.clients.ClientResponseDTO;
import com.example.account.core.clients.UserClient;
import com.example.account.core.enums.MovementTypeEnum;
import com.example.account.modules.movement.application.dto.MovementReportDTO;
import com.example.account.modules.movement.application.dto.MovementResponseDTO;
import com.example.account.modules.movement.application.ports.in.*;
import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.account.modules.account.application.ports.out.AccountRepository;
import com.example.account.modules.account.domain.AccountModel;
import com.example.account.modules.movement.application.adapters.MovementRequestMapper;
import com.example.account.modules.movement.application.dto.MovementRequestDTO;
import com.example.account.modules.movement.application.ports.out.MovementRepository;
import com.example.account.modules.movement.domain.MovementModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementService implements CreateMovementUseCase, UpdateMovementUseCase, DeleteMovementUseCase, RetrieveMovementUseCase, RetrieveMovementsUseCase, GenerateReportUseCase {
    private final MovementRequestMapper mapper;
    private final MovementRepository repository;
    private final AccountRepository accountRepository;
    private final UserClient client;

    @Override
    public MovementResponseDTO create(MovementRequestDTO dto) {
        if (dto.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }
        AccountModel account = accountRepository.findByNumber(dto.getAccountNumber()).orElseThrow();
        MovementModel model = mapper.toModel(dto);
        model.setAccount(account);
        updateBalance(dto.getAmount(), model);
        MovementResponseDTO response = mapper.toResponse(repository.save(model));
        response.setAccountNumber(account.getNumber());
        return response;
    }

    @Override
    public MovementModel update(MovementRequestDTO dto, Long id) {
        MovementModel model = mapper.toModel(dto);
        return repository.update(model, id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public MovementModel retrieve(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<MovementModel> retrieveAll(Long accountId) {
        AccountModel account = accountRepository.findById(accountId);
        return repository.findAllByAccountId(account.getId());
    }

    @Transactional
    private void updateBalance(BigDecimal amount, MovementModel model) {
        Optional<MovementModel> lastMovement = repository.getLastMovement(model.getAccount().getId());
        BigDecimal balance = BigDecimal.ZERO;
        if (lastMovement.isPresent()) {
            balance = lastMovement.get().getBalance();
            if (balance.compareTo(BigDecimal.ZERO) > 0) {
                model.setType(MovementTypeEnum.INCOME);
                model.setBalance(balance.add(model.getAmount()));
            } else {
                if (balance.add(amount).compareTo(BigDecimal.ZERO) < 0) {
                    throw new IllegalArgumentException("Insufficient balance");
                }
                model.setType(MovementTypeEnum.EXPENSE);
                model.setBalance(balance.subtract(model.getAmount()));
            }
        } else {
            model.setType(MovementTypeEnum.INCOME);
            model.setBalance(model.getAccount().getInitialBalance());
        }
    }

    @Override
    public List<MovementReportDTO> generateReport(String dates) {
        List<MovementModel> movements = repository.generateReport(dates);
        if (!movements.isEmpty()) {
            return movements.stream().map(m -> {
                MovementReportDTO dto = new MovementReportDTO();
                dto.setAccountNumber(m.getAccount().getNumber());
                dto.setAmount(m.getAmount());
                dto.setBalance(m.getBalance());
                dto.setDate(m.getDate());
                dto.setType(m.getAccount().getType());
                dto.setInitialBalance(m.getAccount().getInitialBalance());
                try {
                    ClientResponseDTO clientResponseDTO = Objects.requireNonNull(this.client.get(m.getAccount().getClientId()).getBody()).getData();
                    dto.setClient(clientResponseDTO.getName());
                } catch (FeignException.FeignClientException e) {
                    throw new EntityNotFoundException("Client not found");
                }
                return dto;
            }).toList();
        }
        return null;
    }
}
