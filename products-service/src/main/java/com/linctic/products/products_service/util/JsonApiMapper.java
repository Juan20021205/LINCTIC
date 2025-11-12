package com.linctic.products.products_service.util;

import com.linctic.products.products_service.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import java.util.*;

public class JsonApiMapper {

    public static Map<String, Object> single(ProductResponseDTO dto) {
        return Map.of(
                "data", Map.of(
                        "type", "products",
                        "id", dto.getId(),
                        "attributes", Map.of(
                                "name", dto.getName(),
                                "price", dto.getPrice())));
    }

    public static Map<String, Object> paginated(Page<ProductResponseDTO> page) {
        var data = page.getContent().stream()
                .map(dto -> Map.of(
                        "type", "products",
                        "id", dto.getId(),
                        "attributes", Map.of(
                                "name", dto.getName(),
                                "price", dto.getPrice())))
                .toList();

        var meta = Map.of(
                "page", Map.of(
                        "number", page.getNumber(),
                        "size", page.getSize(),
                        "totalElements", page.getTotalElements(),
                        "totalPages", page.getTotalPages()));

        return Map.of("data", data, "meta", meta);
    }
}
