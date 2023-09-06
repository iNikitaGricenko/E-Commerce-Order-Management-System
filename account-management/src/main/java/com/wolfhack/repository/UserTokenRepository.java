package com.wolfhack.repository;

import com.wolfhack.model.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {
}