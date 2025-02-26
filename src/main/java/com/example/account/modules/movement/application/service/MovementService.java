package com.example.account.modules.movement.application.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.account.core.enums.MovementTypeEnum;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.example.account.modules.account.application.ports.out.AccountRepository;
import com.example.account.modules.account.domain.AccountModel;
import com.example.account.modules.movement.application.adapters.MovementRequestMapper;
import com.example.account.modules.movement.application.dto.MovementRequestDTO;
import com.example.account.modules.movement.application.ports.in.CreateMovementUseCase;
import com.example.account.modules.movement.application.ports.in.DeleteMovementUseCase;
import com.example.account.modules.movement.application.ports.in.RetrieveMovementUseCase;
import com.example.account.modules.movement.application.ports.in.RetrieveMovementsUseCase;
import com.example.account.modules.movement.application.ports.in.UpdateMovementUseCase;
import com.example.account.modules.movement.application.ports.out.MovementRepository;
import com.example.account.modules.movement.domain.MovementModel;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovementService implements CreateMovementUseCase, UpdateMovementUseCase, DeleteMovementUseCase, RetrieveMovementUseCase, RetrieveMovementsUseCase {
    private final MovementRequestMapper mapper;
    private final MovementRepository repository;
    private final AccountRepository accountRepository;

    @Override
    public MovementModel create(MovementRequestDTO dto) {
        if (dto.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }
        AccountModel account = accountRepository.findByNumber(dto.getAccountNumber()).orElseThrow();
        MovementModel model = mapper.toModel(dto);
        model.setAccount(account);
        updateBalance(dto.getAmount(), model);
        return repository.save(model);
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
        AccountModel account = accountRepository.findByNumber(model.getAccount().getNumber()).orElseThrow();
        BigDecimal balance = account.getBalance();
        BigDecimal newBalance = balance.add(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(newBalance);
        model.setAccount(accountRepository.update(account, account.getId()));
        model.setType(amount.compareTo(BigDecimal.ZERO) > 0 ? MovementTypeEnum.INCOME : MovementTypeEnum.EXPENSE);
        model.setBalance(newBalance);
    }
}
