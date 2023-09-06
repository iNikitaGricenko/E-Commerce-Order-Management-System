package com.wolfhack.model.entity;

import java.time.LocalDate;

public class OrderEntity {

	private Long id;

	private Long userId;

	private String orderStatus;

	private long totalAmount;

	private LocalDate orderDate;

	private Long paymentId;

	private String shippingAddress;

	private LocalDate createdDate;

	private LocalDate updatedDate;

}
