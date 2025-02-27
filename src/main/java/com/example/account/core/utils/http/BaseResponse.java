package com.example.account.core.utils.http;

public sealed interface BaseResponse<T> permits SuccessResponse, ErrorResponse {
}
