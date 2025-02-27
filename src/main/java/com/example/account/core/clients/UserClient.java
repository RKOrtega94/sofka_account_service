package com.example.account.core.clients;

import com.example.account.core.utils.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-client", url = "${clients.user}")
public interface UserClient {
    @GetMapping("/{id}")
    ResponseEntity<BaseResponse<ClientResponseDTO>> get(@PathVariable("id") Long id);
}
