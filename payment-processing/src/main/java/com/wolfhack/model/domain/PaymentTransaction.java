package com.wolfhack.model.domain;

import com.wolfhack.model.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.wolfhack.model.entity.PaymentTransactionEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTransaction implements Serializable, DomainModel {
	private Long id;
	private Long userId;
	private Long orderId;
	private PaymentStatus paymentStatus;
	private LocalDate paymentDate;
	private long paymentAmount;
	private String paymentMethod;
	private String paymentDetails;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}