package com.example.account.modules.account.infrastructure.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "user-client", url = "${clients.user}")
public interface UserClient {
    @GetMapping("/{id}")
    ClientResponseDTO get(Long id);
}
