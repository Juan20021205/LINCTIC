package com.linctic.products.products_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linctic.products.products_service.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
