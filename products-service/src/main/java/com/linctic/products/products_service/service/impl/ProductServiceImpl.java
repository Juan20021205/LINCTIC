package com.linctic.products.products_service.service.impl;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.linctic.products.products_service.dto.ProductRequestDTO;
import com.linctic.products.products_service.dto.ProductResponseDTO;
import com.linctic.products.products_service.util.ProductMapper;
import com.linctic.products.products_service.model.entity.Product;
import com.linctic.products.products_service.repository.ProductRepository;
import com.linctic.products.products_service.service.interfaces.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

     private final ProductRepository repository;

    @Override
    public ProductResponseDTO create(ProductRequestDTO dto) {
        Product saved = repository.save(ProductMapper.toEntity(dto));
        return ProductMapper.toResponse(saved);
    }

    @Override
    public Optional<ProductResponseDTO> findById(Long id) {
        return repository.findById(id).map(ProductMapper::toResponse);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO dto) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado: " + id));

        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());

        Product updated = repository.save(existing);
        return ProductMapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Producto no encontrado: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Page<ProductResponseDTO> list(Pageable pageable) {
        return repository.findAll(pageable).map(ProductMapper::toResponse);
    }

}
