package com.wolfhack.repository;

import com.wolfhack.model.entity.ProductInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductInventoryRepository extends JpaRepository<ProductInventoryEntity, Long> {
	Optional<ProductInventoryEntity> findByProductId(Long productId);
}