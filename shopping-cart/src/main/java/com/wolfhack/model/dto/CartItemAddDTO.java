package com.wolfhack.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.entity.CartItemEntity}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemAddDTO implements Serializable {
	@Min(1)
	@Positive
	private Long productId;
	@Positive private long quantity;
}