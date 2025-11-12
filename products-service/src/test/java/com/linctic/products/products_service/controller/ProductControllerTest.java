package com.linctic.products.products_service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.linctic.products.products_service.dto.ProductRequestDTO;
import com.linctic.products.products_service.dto.ProductResponseDTO;
import com.linctic.products.products_service.service.interfaces.ProductService;

@ActiveProfiles("test")
@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    @Test
    void shouldCreateAndRetrieveProduct() throws Exception {
        ProductResponseDTO mockResponse = new ProductResponseDTO(1L, "Laptop", BigDecimal.valueOf(1500.00));
        when(service.create(any(ProductRequestDTO.class))).thenReturn(mockResponse);

        String json = """
            {"name":"Laptop","price":1500.00}
        """;

        mockMvc.perform(post("/products")
                .header("X-API-KEY", "test-key")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.attributes.name").value("Laptop"));
    }
}
