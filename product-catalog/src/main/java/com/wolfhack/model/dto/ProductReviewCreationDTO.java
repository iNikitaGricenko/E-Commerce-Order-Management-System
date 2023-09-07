package com.wolfhack.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.domain.ProductReview}
 */
@Value
public class ProductReviewCreationDTO implements Serializable {
	@NotNull
	@Min(1)
	@Positive
	Long userId;

	@Min(0)
	@Max(10)
	@PositiveOrZero
	int rating;

	@NotEmpty
	@NotBlank
	String reviewText;
}