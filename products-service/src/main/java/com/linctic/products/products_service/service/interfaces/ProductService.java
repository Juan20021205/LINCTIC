package com.linctic.products.products_service.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.linctic.products.products_service.dto.ProductRequestDTO;
import com.linctic.products.products_service.dto.ProductResponseDTO;

public interface ProductService {
    /**
     * 
     * @param dto
     * @return
     */
    ProductResponseDTO create(ProductRequestDTO dto);

    /**
     * 
     * @param id
     * @return
     */
    Optional<ProductResponseDTO> findById(Long id);

    /**
     * 
     * @param id
     * @param dto
     * @return
     */
    ProductResponseDTO update(Long id, ProductRequestDTO dto);

    /**
     * 
     * @param id
     */
    void delete(Long id);

    /**
     * 
     * @param pageable
     * @return
     */
    Page<ProductResponseDTO> list(Pageable pageable);
}
