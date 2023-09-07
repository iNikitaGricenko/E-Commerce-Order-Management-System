package com.wolfhack.repository;

import com.wolfhack.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findAllByCategoryId(Long categoryId);
}