package com.wolfhack.payment.repository;

import com.wolfhack.payment.model.entity.PaymentMethodDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodDetails, Long> {
}