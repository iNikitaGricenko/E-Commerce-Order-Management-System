package com.wolfhack.model.domain;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Product implements Serializable, DomainModel {
	private Long id;
	private String name;
	private String description;
	private double unitPrice;
	private Long categoryId;
	private long stockQuantity;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}