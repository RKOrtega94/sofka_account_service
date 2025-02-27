package com.example.account.core.utils.http;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse<T>(String message, HttpStatus code, List<Object> errors) implements BaseResponse<T> {
}
