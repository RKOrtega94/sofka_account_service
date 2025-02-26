package com.example.account.modules.account.infrastructure.api;

import com.example.account.modules.account.application.dto.AccountRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuentas")
public class AccountController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createAccount(@RequestBody @Valid AccountRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody @Valid AccountRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body("Account updated");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account deleted");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> getAccount(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body("Account retrieved");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> getAccounts(Map<String, Object> params) {
        return ResponseEntity.status(HttpStatus.OK).body("Accounts retrieved");
    }
}
