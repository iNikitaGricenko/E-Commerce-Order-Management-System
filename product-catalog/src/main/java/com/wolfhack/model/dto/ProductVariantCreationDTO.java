package com.wolfhack.model.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.domain.ProductVariant}
 */
@Value
public class ProductVariantCreationDTO implements Serializable {
	@NotNull
	@NotEmpty
	@NotBlank
	String name;

	String description;

	@Min(9)
	@Positive
	double price;

	@PositiveOrZero
	long stockQuantity;
}