package com.wolfhack.repository;

import com.wolfhack.model.entity.ProductReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReviewEntity, Long> {
	List<ProductReviewEntity> findAllByProductId(Long productId);
}