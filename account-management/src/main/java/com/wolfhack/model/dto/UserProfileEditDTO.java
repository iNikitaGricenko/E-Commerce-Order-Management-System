package com.wolfhack.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.wolfhack.model.domain.User}
 */
@Value
public class UserProfileEditDTO implements Serializable {
	String username;
	String email;
	String firstName;
	String lastName;
	String phoneNumber;
	String address;
	String password;
}