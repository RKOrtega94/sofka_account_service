package com.example.account.modules.account.application.service;

import com.example.account.modules.account.application.adapters.AccountRequestMapper;
import com.example.account.modules.account.application.dto.CreateAccountRequestDTO;
import com.example.account.modules.account.application.dto.UpdateAccountRequestDTO;
import com.example.account.modules.account.application.ports.in.*;
import com.example.account.modules.account.application.ports.out.AccountRepository;
import com.example.account.modules.account.domain.AccountModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountService implements CreateAccountUseCase, UpdateAccountUseCase, DeleteAccountUseCase, RetrieveAccountsUseCase, RetrieveAccountUseCase {
    private final AccountRequestMapper mapper;
    private final AccountRepository repository;

    @Override
    public AccountModel create(CreateAccountRequestDTO dto) {
        AccountModel accountModel = mapper.toAccountModel(dto);
        String accountNumber;
        do {
            accountNumber = generateAccountNumber();
        } while (repository.findByNumber(accountNumber).isPresent());
        accountModel.setNumber(accountNumber);
        return repository.save(accountModel);
    }


    @Override
    public AccountModel update(UpdateAccountRequestDTO dto, Long id) {
        AccountModel accountModel = mapper.toAccountModel(dto);
        return repository.update(accountModel, id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<AccountModel> retrieve(Map<String, Object> params) {
        return repository.findAll(params);
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
}
