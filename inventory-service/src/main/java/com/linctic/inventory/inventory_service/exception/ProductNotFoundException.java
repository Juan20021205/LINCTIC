package com.linctic.inventory.inventory_service.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long productId) {
        super("Producto no encontrado con ID " + productId);
    }
}
