package com.example.account.core.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HttpExceptionHandler {
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleInvalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e) {
        return getRootCauseMessage(e);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return getRootCauseMessage(e);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //noinspection DataFlowIssue
        return e.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacement) -> existing));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return Map.of("error", "Database constraint violation: " + getRootCauseMessage(e));
    }


    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e) {
        return Map.of("error", "Invalid data access resource usage: " + getRootCauseMessage(e));
    }


    @ExceptionHandler(JpaSystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleJpaSystemException(JpaSystemException e) {
        return Map.of("error", "JPA system exception: " + getRootCauseMessage(e));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleIllegalArgumentException(IllegalArgumentException e) {
        return Map.of("error", "Illegal argument: " + getRootCauseMessage(e));
    }

    private String getRootCauseMessage(Throwable e) {
        Throwable rootCause = e.getCause();
        while (rootCause != null && rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        return rootCause != null ? rootCause.getMessage() : e.getMessage();
    }
}
