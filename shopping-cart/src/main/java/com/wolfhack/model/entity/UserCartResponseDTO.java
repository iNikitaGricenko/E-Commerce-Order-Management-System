package com.wolfhack.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link UserCartEntity}
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserCartResponseDTO implements Serializable {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("user_id")
	private Long userId;

	@JsonProperty("created_date")
	private LocalDate createdDate;

	@JsonProperty("cart_items")
	private List<CartItemResponseDTO> cartItems;
}