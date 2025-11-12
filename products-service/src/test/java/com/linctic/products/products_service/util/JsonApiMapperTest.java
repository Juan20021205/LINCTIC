package com.linctic.products.products_service.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.linctic.products.products_service.dto.ProductResponseDTO;

public class JsonApiMapperTest {
    @Test
    void single_shouldWrapResponseCorrectly() {
        var dto = new ProductResponseDTO(1L, "Laptop", BigDecimal.valueOf(1200));
        var response = JsonApiMapper.single(dto);
        assertTrue(response.containsKey("data"));
    }
}
