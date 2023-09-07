package com.wolfhack.model.dto;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.wolfhack.model.domain.User}
 */
@Value
public class UserRegisteredNotificationDTO implements Serializable {

	String email;

	String firstName;

	String lastName;

	LocalDate birthDate;

	String address;
}