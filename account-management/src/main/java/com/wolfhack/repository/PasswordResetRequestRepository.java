package com.wolfhack.repository;

import com.wolfhack.model.entity.PasswordResetRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRequestRepository extends JpaRepository<PasswordResetRequestEntity, Long> {
}