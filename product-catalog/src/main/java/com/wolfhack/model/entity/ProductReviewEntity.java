package com.wolfhack.model.entity;

import java.time.LocalDate;

public class ProductReviewEntity {

	private Long id;

	private Long productId;

	private Long userId;

	private int rating;

	private String reviewText;

	private LocalDate reviewDate;

}
