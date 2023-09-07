package com.wolfhack.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem implements Serializable, DomainModel {
	private Long id;
	private Long cartId;
	private Long productId;
	private long quantity;
	private double unitPrice;
	private LocalDate createdDate;
	private LocalDate updatedDate;
}