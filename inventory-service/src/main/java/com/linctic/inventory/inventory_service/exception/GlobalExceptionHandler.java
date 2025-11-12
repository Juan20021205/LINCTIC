package com.linctic.inventory.inventory_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.linctic.inventory.inventory_service.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleProductNotFound(ProductNotFoundException ex) {
        return ResponseEntity.status(404)
                .body(ApiResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ApiResponse<?>> handleInsufficientStock(InsufficientStockException ex) {
        return ResponseEntity.status(400)
                .body(ApiResponse.builder()
                        .success(false)
                        .message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneral(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(ApiResponse.builder()
                        .success(false)
                        .message("Error inesperado: " + ex.getMessage())
                        .build());
    }
}
