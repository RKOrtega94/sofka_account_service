package com.example.account.modules.movement.infrastructure.api;

import com.example.account.core.utils.http.BaseResponse;
import com.example.account.core.utils.http.SuccessResponse;
import com.example.account.modules.movement.application.dto.MovementReportDTO;
import com.example.account.modules.movement.application.dto.MovementRequestDTO;
import com.example.account.modules.movement.application.dto.MovementResponseDTO;
import com.example.account.modules.movement.application.ports.in.*;
import com.example.account.modules.movement.domain.MovementModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movimientos")
public class MovementController {
    private final CreateMovementUseCase createMovementUseCase;
    private final UpdateMovementUseCase updateMovementUseCase;
    private final DeleteMovementUseCase deleteMovementUseCase;
    private final RetrieveMovementUseCase retrieveMovementUseCase;
    private final RetrieveMovementsUseCase retrieveMovementsUseCase;
    private final GenerateReportUseCase generateReportUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<BaseResponse<MovementResponseDTO>> createMovement(@RequestBody @Valid MovementRequestDTO dto) {
        BaseResponse<MovementResponseDTO> response = new SuccessResponse<>(createMovementUseCase.create(dto), "Movement created successfully", HttpStatus.CREATED);
        return ResponseEntity.created(URI.create("/api/movimientos")).body(response);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<MovementModel>> updateMovement(@PathVariable Long id, @RequestBody @Valid MovementRequestDTO dto) {
        BaseResponse<MovementModel> response = new SuccessResponse<>(updateMovementUseCase.update(dto, id), "Movement updated successfully", HttpStatus.OK);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        deleteMovementUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<MovementModel>> getMovement(@PathVariable Long id) {
        BaseResponse<MovementModel> response = new SuccessResponse<>(retrieveMovementUseCase.retrieve(id), "Movement retrieved successfully", HttpStatus.OK);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<List<MovementModel>>> getMovements(Map<String, Object> params) {
        BaseResponse<List<MovementModel>> response = new SuccessResponse<>(retrieveMovementsUseCase.retrieveAll(Long.parseLong(params.get("id").toString())), "Movements retrieved successfully", HttpStatus.OK);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/reportes")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<List<MovementReportDTO>>> getReport(@RequestParam(name = "fechas") @Valid @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2},\\d{4}-\\d{2}-\\d{2}$", message = "The date format must be yyyy-MM-dd,yyyy-MM-dd") String request) {
        BaseResponse<List<MovementReportDTO>> response = new SuccessResponse<>(generateReportUseCase.generateReport(request), "Report generated successfully", HttpStatus.OK);
        return ResponseEntity.ok().body(response);
    }
}
