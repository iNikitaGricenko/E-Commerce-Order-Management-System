package com.wolfhack.model.entity;

import java.time.LocalDate;

public class PaymentTransactionEntity {

	private Long id;

	private Long userId;

	private Long orderId;

	private String paymentStatus;

	private LocalDate paymentDate;

	private long paymentAmount;

	private String paymentMethod;

	private String paymentDetails;

	private LocalDate createdDate;

	private LocalDate updatedDate;

}
