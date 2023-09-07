package com.wolfhack.model.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductVariant implements Serializable, DomainModel {
	private Long id;
	private Long productId;
	private String variantName;
	private String variantDescription;
	private double variantPrice;
	private long variantStockQuantity;
}