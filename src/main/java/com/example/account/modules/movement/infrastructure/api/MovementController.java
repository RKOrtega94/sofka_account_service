package com.example.account.modules.movement.infrastructure.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movimientos")
public class MovementController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovement() {
        System.out.println("Create movement");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovement() {
        System.out.println("Update movement");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovement() {
        System.out.println("Delete movement");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getMovement() {
        System.out.println("Get movement");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getMovements() {
        System.out.println("Get movements");
    }
}
