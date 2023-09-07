package com.wolfhack.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.wolfhack.model.domain.ProductReview}
 */
@Value
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductReviewResponseDTO implements Serializable {
	@JsonProperty("id")
	Long id;

	@JsonProperty("user_id")
	Long userId;

	@JsonProperty("rating")
	int rating;

	@JsonProperty("text")
	String reviewText;

	@JsonProperty("created_at")
	LocalDate reviewDate;
}