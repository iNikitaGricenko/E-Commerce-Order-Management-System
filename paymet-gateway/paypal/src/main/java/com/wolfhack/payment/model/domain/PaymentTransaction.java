package com.wolfhack.payment.model.domain;

import com.wolfhack.payment.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransaction implements Serializable, DomainModel {
	private Long id;
	private Long userId;
	private Long orderId;
	private long paymentAmount;
	private PaymentStatus paymentStatus;
	private LocalDate transactionDate;
	private String paymentReference;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}