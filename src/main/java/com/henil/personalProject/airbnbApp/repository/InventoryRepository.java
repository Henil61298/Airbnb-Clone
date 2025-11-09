package com.henil.personalProject.airbnbApp.repository;

import com.henil.personalProject.airbnbApp.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}