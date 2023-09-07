package com.wolfhack.repository;

import com.wolfhack.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
	List<CartItemEntity> findAllByCartId(Long cartId);
}