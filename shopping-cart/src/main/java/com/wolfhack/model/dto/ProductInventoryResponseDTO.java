package com.wolfhack.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductInventoryResponseDTO implements Serializable {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("product")
	private ProductFullResponseDTO product;

	@JsonProperty("name")
	private String name;

	@JsonProperty("available_quantity")
	private long availableQuantity;

	@JsonProperty("reserved_quantity")
	private long reservedQuantity;

	@JsonProperty("created_date")
	private LocalDate createdDate;

	@JsonProperty("updated_date")
	private LocalDate updatedDate;
}