package com.wolfhack.repository;

import com.wolfhack.model.entity.UserSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSessionRepository extends JpaRepository<UserSessionEntity, Long> {
}