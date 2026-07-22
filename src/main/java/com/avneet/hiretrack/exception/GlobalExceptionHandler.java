package com.avneet.hiretrack.exception;

import com.avneet.hiretrack.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFound(
            ResourceNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponse.<String>builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Validation failed");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiResponse.<String>builder()
                                .success(false)
                                .message(errorMessage)
                                .data(null)
                                .build()
                );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidRequest(
            HttpMessageNotReadableException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(
                        ApiResponse.<String>builder()
                                .success(false)
                                .message("Invalid request data")
                                .data(null)
                                .build()
                );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccessDenied(
            AccessDeniedException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ApiResponse.<String>builder()
                                .success(false)
                                .message("Access Denied")
                                .data(null)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiResponse.<String>builder()
                                .success(false)
                                .message(ex.getMessage())
                                .data(null)
                                .build()
                );
    }
}