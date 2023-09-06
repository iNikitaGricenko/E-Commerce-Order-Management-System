package com.wolfhack.model.entity;

import java.time.LocalDate;

public class CartItemEntity {

	private Long id;

	private Long cartId;

	private Long productId;

	private long quantity;

	private double unitPrice;

	private LocalDate createdDate;

	private LocalDate updatedDate;

}
