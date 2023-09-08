package com.wolfhack.payment.repository;

import com.wolfhack.payment.model.entity.PaymentLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentLogRepository extends JpaRepository<PaymentLogEntity, Long> {
}