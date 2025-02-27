package com.example.account.core.utils.http;

import org.springframework.http.HttpStatus;

public record SuccessResponse<T>(T data, String message, HttpStatus code) implements BaseResponse<T> {
}
