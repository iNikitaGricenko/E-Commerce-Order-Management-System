package com.wolfhack.repository;

import com.wolfhack.model.entity.UserCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCartRepository extends JpaRepository<UserCartEntity, Long> {
	Optional<UserCartEntity> findByUserId(Long userId);
}