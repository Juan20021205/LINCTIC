package com.linctic.inventory.inventory_service.service.interfaces;

import java.util.Map;

public interface InventoryService {
    Map<String, Object> getInventoryWithProduct(Long productId);

    Map<String, Object> updateInventory(Long productId, int quantity);

    Map<String, Object> reduceInventory(Long productId, int quantity);
}
