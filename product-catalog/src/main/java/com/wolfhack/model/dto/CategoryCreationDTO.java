package com.wolfhack.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.domain.Category}
 */
@Value
public class CategoryCreationDTO implements Serializable {
	@NotNull
	@NotEmpty
	@NotBlank
	String name;
}