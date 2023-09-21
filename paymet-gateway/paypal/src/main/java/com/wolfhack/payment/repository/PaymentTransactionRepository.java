package com.wolfhack.payment.repository;

import com.wolfhack.payment.model.entity.PaymentTransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransactionRepository extends JpaRepository<PaymentTransactionLog, Long> {
}