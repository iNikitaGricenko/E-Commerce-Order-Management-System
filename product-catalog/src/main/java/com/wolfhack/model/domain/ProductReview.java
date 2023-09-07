package com.wolfhack.model.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ProductReview implements Serializable, DomainModel {
	private Long id;
	private Long productId;
	private Long userId;
	private int rating;
	private String reviewText;
	private LocalDate reviewDate;
}