package com.wolfhack.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfhack.model.domain.DomainModel;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductFullResponseDTO implements Serializable, DomainModel {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("description")
	private String description;

	@JsonProperty("unit_price")
	private double unitPrice;

	@JsonProperty("category_id")
	private Long categoryId;

	@JsonProperty("category_name")
	private String categoryName;

	@JsonProperty("stock_quantity")
	private long stockQuantity;

	@JsonProperty("created_date")
	private LocalDate createdDate;

	@JsonProperty("updated_date")
	private LocalDate updatedDate;
}