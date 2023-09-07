package com.wolfhack.model.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.wolfhack.model.domain.User}
 */
@Value
public class UserResetNotificationDTO implements Serializable {

	Long id;

	String email;

	String username;

	String resetToken;
}