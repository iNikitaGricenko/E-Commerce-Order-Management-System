package com.wolfhack.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.domain.ProductVariant}
 */
@Value
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductVariantResponseDTO implements Serializable {

	@JsonProperty("name")
	String name;

	@JsonProperty("description")
	String description;

	@JsonProperty("price")
	double price;

	@JsonProperty("stock_quantity")
	long stockQuantity;
}