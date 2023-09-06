package com.wolfhack.model.entity;

import java.time.LocalDate;
import java.util.List;

public class ProductEntity {

	private Long id;

	private String name;

	private String description;

	private double unitPrice;

	private Long categoryId;

	private double stockQuantity;

	private LocalDate createdDate;

	private LocalDate updatedDate;

}
