package com.example.account.modules.account.application.ports.in;

import com.example.account.modules.account.domain.AccountModel;

import java.util.List;

public interface StatementReportUseCase {
    List<AccountModel> report(String dates);
}
