package com.wolfhack.repository;

import com.wolfhack.model.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {
	Optional<UserTokenEntity> findByToken(String token);

	Optional<UserTokenEntity> findByUserId(Long id);

	boolean existsByUserId(Long userId);
}