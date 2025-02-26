package com.example.account.modules.account.infrastructure.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuentas")
public class AccountController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<?> createAccount() {
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> updateAccount() {
        return ResponseEntity.status(HttpStatus.OK).body("Account updated");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<?> deleteAccount() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account deleted");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> getAccount() {
        return ResponseEntity.status(HttpStatus.OK).body("Account retrieved");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<?> getAccounts() {
        return ResponseEntity.status(HttpStatus.OK).body("Accounts retrieved");
    }
}
