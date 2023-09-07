package com.wolfhack.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.domain.ProductImage}
 */
@Value
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductImageResponseDTO implements Serializable {

	@JsonProperty("image_url")
	String imageUrl;

	@JsonProperty("image_description")
	String description;
}