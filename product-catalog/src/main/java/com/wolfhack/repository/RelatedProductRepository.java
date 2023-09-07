package com.wolfhack.repository;

import com.wolfhack.model.entity.RelatedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelatedProductRepository extends JpaRepository<RelatedProductEntity, Long> {
}