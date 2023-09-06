package com.wolfhack.model.entity;

import java.time.LocalDate;

public class OrderItemsEntity {

	private Long id;

	private Long orderId;

	private Long productId;

	private long quantity;

	private double unitPrice;

	private LocalDate createdDate;

	private LocalDate updatedDate;

}
