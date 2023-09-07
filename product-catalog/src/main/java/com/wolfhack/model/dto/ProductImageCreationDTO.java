package com.wolfhack.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.domain.ProductImage}
 */
@Value
public class ProductImageCreationDTO implements Serializable {
	@NotNull
	@NotEmpty
	@NotBlank
	@URL
	String imageUrl;
	String description;
}