package com.linctic.inventory.inventory_service.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linctic.inventory.inventory_service.client.ProductClient;
import com.linctic.inventory.inventory_service.dto.InventoryResponseDTO;
import com.linctic.inventory.inventory_service.dto.ProductResponseDTO;
import com.linctic.inventory.inventory_service.exception.InsufficientStockException;
import com.linctic.inventory.inventory_service.exception.ProductNotFoundException;
import com.linctic.inventory.inventory_service.model.entity.Inventory;
import com.linctic.inventory.inventory_service.repository.InventoryRepository;
import com.linctic.inventory.inventory_service.service.interfaces.InventoryService;
import com.linctic.inventory.inventory_service.util.JsonApiInventoryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final ProductClient productClient;

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getInventoryWithProduct(Long productId) {
        Inventory inventory = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        ProductResponseDTO product = fetchProductSafely(productId);
        InventoryResponseDTO dto = new InventoryResponseDTO(
                inventory.getProductId(),
                inventory.getQuantity(),
                product);

        return JsonApiInventoryMapper.single(dto);
    }

    @Override
    @Transactional
    public Map<String, Object> updateInventory(Long productId, int quantity) {
        Inventory inventory = repository.findById(productId)
                .orElseGet(() -> new Inventory(productId, 0));

        inventory.setQuantity(quantity);
        repository.save(inventory);

        ProductResponseDTO product = fetchProductSafely(productId);
        emitInventoryChangedEvent(productId, quantity);

        InventoryResponseDTO dto = new InventoryResponseDTO(productId, quantity, product);
        return JsonApiInventoryMapper.single(dto);
    }

    @Override
    @Transactional
    public Map<String, Object> reduceInventory(Long productId, int quantity) {
        Inventory inventory = repository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        int newQty = inventory.getQuantity() - quantity;
        if (newQty < 0)
            throw new InsufficientStockException(productId);

        inventory.setQuantity(newQty);
        repository.save(inventory);

        ProductResponseDTO product = fetchProductSafely(productId);
        emitInventoryChangedEvent(productId, newQty);

        InventoryResponseDTO dto = new InventoryResponseDTO(productId, newQty, product);
        return JsonApiInventoryMapper.single(dto);
    }

    private ProductResponseDTO fetchProductSafely(Long productId) {
        try {
            return productClient.getProduct(productId);
        } catch (Exception e) {
            log.warn("No se pudo obtener información del producto {}: {}", productId, e.getMessage());
            return null;
        }
    }

    private void emitInventoryChangedEvent(Long productId, int newQuantity) {
        log.info("INVENTORY_CHANGED: product={} newQty={}", productId, newQuantity);
    }
}
