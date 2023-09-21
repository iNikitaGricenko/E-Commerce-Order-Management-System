package com.wolfhack.payment.repository;

import com.wolfhack.payment.model.entity.ErrorLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository<ErrorLogEntity, Long> {
}