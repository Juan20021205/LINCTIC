package com.linctic.inventory.inventory_service.util;

import com.linctic.inventory.inventory_service.dto.InventoryResponseDTO;
import java.util.Map;

public class JsonApiInventoryMapper {
    public static Map<String, Object> single(InventoryResponseDTO dto) {
        return Map.of(
                "data", Map.of(
                        "type", "inventories",
                        "id", dto.getProductId(),
                        "attributes", Map.of(
                                "quantity", dto.getQuantity(),
                                "product", dto.getProduct()
                        )
                )
        );
    }
}
