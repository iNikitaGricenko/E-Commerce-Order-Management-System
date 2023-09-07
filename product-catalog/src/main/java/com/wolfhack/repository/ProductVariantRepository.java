package com.wolfhack.repository;

import com.wolfhack.model.entity.ProductVariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVariantRepository extends JpaRepository<ProductVariantEntity, Long> {
	List<ProductVariantEntity> findAllByProductId(Long productId);
}