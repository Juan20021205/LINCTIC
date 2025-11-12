package com.linctic.products.products_service.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private BigDecimal price;
}
