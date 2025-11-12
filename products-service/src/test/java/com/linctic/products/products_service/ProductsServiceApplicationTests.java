package com.linctic.products.products_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import com.linctic.products.products_service.config.TestSecurityConfig;

@SpringBootTest
@ActiveProfiles("test")
@Import(TestSecurityConfig.class)
class ProductsServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}
