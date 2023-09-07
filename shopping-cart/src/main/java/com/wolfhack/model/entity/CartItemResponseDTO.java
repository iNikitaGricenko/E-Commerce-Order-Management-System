package com.wolfhack.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link CartItemEntity}
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CartItemResponseDTO implements Serializable {
	@JsonProperty("product_id")
	private Long productId;

	@JsonProperty("quantity")
	private long quantity;

	@JsonProperty("unit_price")
	private double unitPrice;

	@JsonProperty("created_date")
	private LocalDate createdDate;
}