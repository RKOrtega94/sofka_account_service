package com.example.account.modules.movement.infrastructure.api;

import com.example.account.core.utils.BaseResponse;
import com.example.account.modules.movement.application.dto.MovementRequestDTO;
import com.example.account.modules.movement.application.ports.in.*;
import com.example.account.modules.movement.domain.MovementModel;
import jakarta.validation.Valid;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<BaseResponse<MovementModel>> createMovement(@RequestBody @Valid MovementRequestDTO dto) {
        return ResponseEntity.created(URI.create("/api/movimientos")).body(BaseResponse.<MovementModel>builder().message("Movement created successfully").data(createMovementUseCase.create(dto)).build());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<MovementModel>> updateMovement(@PathVariable Long id, @RequestBody @Valid MovementRequestDTO dto) {
        return ResponseEntity.ok().body(BaseResponse.<MovementModel>builder().message("Movement updated successfully").data(updateMovementUseCase.update(dto, id)).build());
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
        return ResponseEntity.ok().body(BaseResponse.<MovementModel>builder().message("Movement retrieved successfully").data(retrieveMovementUseCase.retrieve(id)).build());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BaseResponse<List<MovementModel>>> getMovements(Map<String, Object> params) {
        return ResponseEntity.ok().body(BaseResponse.<List<MovementModel>>builder().message("Movements retrieved successfully").data(retrieveMovementsUseCase.retrieveAll(1L)).build());
    }
}
