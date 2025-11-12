package com.linctic.products.products_service.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
            .title("Products API")
            .description("Microservicio de gestión de productos usando JSON:API")
            .version("1.0.0"));
    }
}
