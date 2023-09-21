package com.wolfhack.payment.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInformation implements Serializable, DomainModel {
	private Long id;
	private String reference;
	private Long userId;
	private String cardHolderName;
	private String cardType;
	private String cardLast4Digits;
	private String billingAddress;
	private Long paymentMethodId;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}