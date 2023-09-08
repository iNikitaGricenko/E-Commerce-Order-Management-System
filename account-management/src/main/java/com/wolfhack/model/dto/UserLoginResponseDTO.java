package com.wolfhack.model.dto;

import com.wolfhack.model.Role;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.wolfhack.model.domain.User}
 */
@Value
public class UserLoginResponseDTO implements Serializable {
	Long id;
	String username;
	String email;
	String phoneNumber;
	Role role;
	LocalDate lastLoginDate;
}