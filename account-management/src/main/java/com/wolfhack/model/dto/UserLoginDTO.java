package com.wolfhack.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.domain.User}
 */
@Value
public class UserLoginDTO implements Serializable {

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 5)
	String username;

	@NotNull
	@NotEmpty
	@NotBlank
	@Size(min = 7)
	String password;

}
