package com.wolfhack.model.domain;

import com.wolfhack.model.PaymentMethod;
import com.wolfhack.model.PaymentStatus;
import com.wolfhack.model.dto.PaymentCreationDTO;
import com.wolfhack.model.dto.PaymentMethodCreateDTO;
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

	private LocalDate expirationDate;
	private String cardNumber;
	private String cardHolderName;
	private String cardType;
	private String cardLast4Digits;
	private String billingAddress;

	public void create() {
		this.createdDate = LocalDate.now();
		this.paymentStatus = PaymentStatus.PENDING;
	}

	public PaymentCreationDTO toDto() {
		return PaymentCreationDTO.builder()
				.userId(userId)
				.orderId(orderId)
				.paymentAmount(paymentAmount)
				.paymentDetails(paymentDetails)
				.paymentMethodCreateDTO(PaymentMethodCreateDTO.builder()
						.expirationDate(expirationDate)
						.cardNumber(cardNumber)
						.cardHolderName(cardHolderName)
						.cardType(cardType)
						.cardLast4Digits(cardLast4Digits)
						.billingAddress(billingAddress)
						.build())
				.build();
	}
}