package com.linctic.inventory.inventory_service.client;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.linctic.inventory.inventory_service.dto.ProductResponseDTO;
import com.linctic.inventory.inventory_service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Component
@RequiredArgsConstructor
public class ProductClient {

    private final WebClient webClient;

    @Value("${app.api.key}")
    private String apiKey;

    public ProductResponseDTO getProduct(Long id) {
        return webClient.get()
                .uri("/products/{id}", id)
                .header("X-API-KEY", apiKey)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        response -> Mono.error(new ProductNotFoundException(id)))
                .bodyToMono(ProductResponseDTO.class)
                .retry(1)
                .timeout(Duration.ofSeconds(3))
                .doOnError(e -> log.warn("Error consultando producto {}: {}", id, e.getMessage()))
                .block();
    }

}
