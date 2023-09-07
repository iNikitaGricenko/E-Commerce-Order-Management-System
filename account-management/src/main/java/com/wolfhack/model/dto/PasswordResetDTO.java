package com.wolfhack.model.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;

@Value
public class PasswordResetDTO implements Serializable {

	@NotNull
	@Pattern(regexp = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$")
	@Email
	@NotEmpty
	@NotBlank
	String email;

}
