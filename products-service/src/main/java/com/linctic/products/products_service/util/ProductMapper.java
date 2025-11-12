package com.linctic.products.products_service.util;

import com.linctic.products.products_service.dto.ProductRequestDTO;
import com.linctic.products.products_service.dto.ProductResponseDTO;
import com.linctic.products.products_service.model.entity.Product;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class ProductMapper {
    public static Product toEntity(ProductRequestDTO dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    public static ProductResponseDTO toResponse(Product entity) {
        return new ProductResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice());
    }
}
