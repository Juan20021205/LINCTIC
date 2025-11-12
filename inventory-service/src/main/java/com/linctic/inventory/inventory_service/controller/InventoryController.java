package com.linctic.inventory.inventory_service.controller;

import com.linctic.inventory.inventory_service.service.interfaces.InventoryService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/{productId}")
    public ResponseEntity<Map<String, Object>> getInventory(@PathVariable Long productId) {
        var response = service.getInventoryWithProduct(productId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Map<String, Object>> updateInventory(
            @PathVariable Long productId,
            @RequestParam int quantity) {
        var response = service.updateInventory(productId, quantity);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{productId}/reduce")
    public ResponseEntity<Map<String, Object>> reduceInventory(
            @PathVariable Long productId,
            @RequestParam int quantity) {
        var response = service.reduceInventory(productId, quantity);
        return ResponseEntity.ok(response);
    }
}
