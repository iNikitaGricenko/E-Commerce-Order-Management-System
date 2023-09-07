package com.wolfhack.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfhack.model.domain.DomainModel;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductResponseDTO implements Serializable, DomainModel {

	@JsonProperty("id")
	Long id;

	@JsonProperty("name")
	String name;

	@JsonProperty("description")
	String description;

	@JsonProperty("unit_price")
	double unitPrice;

	@JsonProperty("category_id")
	Long categoryId;

	@JsonProperty("stock_quantity")
	long stockQuantity;

	@JsonProperty("created_date")
	LocalDate createdDate;

	@JsonProperty("updated_date")
	LocalDate updatedDate;
}