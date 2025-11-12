package com.linctic.products.products_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.linctic.products.products_service.dto.ProductRequestDTO;
import com.linctic.products.products_service.service.interfaces.ProductService;
import com.linctic.products.products_service.util.JsonApiMapper;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody ProductRequestDTO dto) {
        var saved = service.create(dto);
        return ResponseEntity.status(201).body(JsonApiMapper.single(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(dto -> ResponseEntity.ok(JsonApiMapper.single(dto)))
                .orElse(ResponseEntity.status(404).body(Map.of(
                    "errors", java.util.List.of(Map.of(
                        "status", "404",
                        "title", "Product not found"
                    ))
                )));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        var updated = service.update(id, dto);
        return ResponseEntity.ok(JsonApiMapper.single(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var result = service.list(PageRequest.of(page, size));
        return ResponseEntity.ok(JsonApiMapper.paginated(result));
    }
}