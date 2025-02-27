package com.example.account.modules.movement.application.ports.in;

import com.example.account.modules.movement.application.dto.MovementReportDTO;
import com.example.account.modules.movement.domain.MovementModel;

import java.util.List;

public interface GenerateReportUseCase {
    List<MovementReportDTO> generateReport(String dates);
}
