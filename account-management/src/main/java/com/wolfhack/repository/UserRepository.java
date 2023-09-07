package com.wolfhack.repository;

import com.wolfhack.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);

	Optional<UserEntity> findByEmail(String email);
}