package com.wolfhack.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.domain.Product}
 */
@Value
public class ProductCreationDTO implements Serializable {
	@NotNull
	@NotEmpty
	@NotBlank
	String name;

	String description;

	@Min(9)
	@Positive
	double unitPrice;
	Long categoryId;

	@PositiveOrZero
	long stockQuantity;
}