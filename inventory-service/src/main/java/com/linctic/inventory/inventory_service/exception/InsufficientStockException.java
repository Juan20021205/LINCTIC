package com.linctic.inventory.inventory_service.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(Long productId) {
        super("Inventario insuficiente para el producto " + productId);
    }
}
