package com.example.hospitalApplication.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> exception(Exception e) {
        HttpStatus status;

        if (e instanceof NullPointerException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (e instanceof IllegalArgumentException) {
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        } else if (e instanceof AccessDeniedException || e instanceof ExpiredJwtException) {
            status = HttpStatus.FORBIDDEN;
        } else if (e instanceof NoSuchElementException) {
            status = HttpStatus.NOT_FOUND;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        ApiErrorDto error = new ApiErrorDto(status, e.toString());
        return new ResponseEntity<>(error, status);
    }
}
