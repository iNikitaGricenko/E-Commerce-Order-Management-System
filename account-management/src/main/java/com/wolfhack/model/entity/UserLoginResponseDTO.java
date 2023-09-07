package com.wolfhack.model.entity;

import com.wolfhack.model.Role;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link UserEntity}
 */
@Value
public class UserLoginResponseDTO implements Serializable {
	Long id;
	String username;
	String email;
	Role role;
	LocalDate lastLoginDate;
}