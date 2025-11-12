package com.linctic.products.products_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.linctic.products.products_service.dto.ProductRequestDTO;
import com.linctic.products.products_service.dto.ProductResponseDTO;
import com.linctic.products.products_service.model.entity.Product;
import com.linctic.products.products_service.repository.ProductRepository;
import com.linctic.products.products_service.service.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    @Test
    void create_shouldReturnSavedProduct() {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setName("Mouse");
        dto.setPrice(BigDecimal.valueOf(25));

        Product saved = new Product(1L, "Mouse", BigDecimal.valueOf(25));
        when(repository.save(any(Product.class))).thenReturn(saved);

        ProductResponseDTO result = service.create(dto);

        assertEquals("Mouse", result.getName());
        assertEquals(BigDecimal.valueOf(25), result.getPrice());
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_shouldReturnProduct_whenExists() {
        Product product = new Product(1L, "Keyboard", BigDecimal.valueOf(45));
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        Optional<ProductResponseDTO> result = service.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Keyboard", result.get().getName());
    }

    @Test
    void findById_shouldReturnEmpty_whenNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<ProductResponseDTO> result = service.findById(99L);

        assertTrue(result.isEmpty());
    }

    @Test
    void update_shouldUpdateExistingProduct() {
        Product existing = new Product(1L, "Old", BigDecimal.valueOf(10));
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setName("New");
        dto.setPrice(BigDecimal.valueOf(20));

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Product.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProductResponseDTO result = service.update(1L, dto);

        assertEquals("New", result.getName());
        assertEquals(BigDecimal.valueOf(20), result.getPrice());
    }

    @Test
    void update_shouldThrowException_whenNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setName("Updated");
        dto.setPrice(BigDecimal.TEN);

        assertThrows(IllegalArgumentException.class, () -> service.update(1L, dto));
    }

    @Test
    void delete_shouldRemoveProduct_whenExists() {
        when(repository.existsById(1L)).thenReturn(true);

        service.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void delete_shouldThrowException_whenNotFound() {
        when(repository.existsById(99L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> service.delete(99L));
    }

    @Test
    void list_shouldReturnPageOfProducts() {
        List<Product> products = List.of(
                new Product(1L, "Mouse", BigDecimal.valueOf(25)),
                new Product(2L, "Keyboard", BigDecimal.valueOf(45)));
        Page<Product> page = new PageImpl<>(products);

        when(repository.findAll(any(PageRequest.class))).thenReturn(page);

        Page<ProductResponseDTO> result = service.list(PageRequest.of(0, 10));

        assertEquals(2, result.getContent().size());
        assertEquals("Keyboard", result.getContent().get(1).getName());
    }
}
