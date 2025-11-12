package com.linctic.inventory.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.linctic.inventory.inventory_service.model.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
