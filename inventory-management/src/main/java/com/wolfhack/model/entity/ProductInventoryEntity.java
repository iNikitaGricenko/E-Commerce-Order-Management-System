package com.wolfhack.model.entity;

import java.time.LocalDate;

public class ProductInventoryEntity {

	private Long id;

	private Long productId;

	private String name;

	private long availableQuantity;

	private long reservedQuantity;

	private LocalDate createdDate;

	private LocalDate updatedDate;

}
