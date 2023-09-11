package com.wolfhack.model.domain;

import com.wolfhack.model.PaymentMethod;
import com.wolfhack.model.PaymentStatus;
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
	private PaymentStatus paymentStatus;
	private LocalDate paymentDate;
	private long paymentAmount;
	private PaymentMethod paymentMethod;
	private String paymentDetails;
	private LocalDate createdDate;
	private LocalDate updatedDate;

	public void create() {
		this.createdDate = LocalDate.now();
		this.paymentStatus = PaymentStatus.PENDING;
	}
}