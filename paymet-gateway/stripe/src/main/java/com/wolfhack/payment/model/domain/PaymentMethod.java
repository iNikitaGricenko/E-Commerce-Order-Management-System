package com.wolfhack.payment.model.domain;

import com.wolfhack.payment.model.PaymentMethodType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod implements Serializable, DomainModel {
	private Long id;
	private String reference;
	private Long userId;
	private PaymentMethodType paymentMethodType;
	private LocalDate expirationDate;
	private Boolean isDefault;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}