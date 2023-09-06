package com.wolfhack.model.entity;

import com.wolfhack.model.Role;

import java.time.LocalDate;

public class UserEntity {

	private Long id;

	private String username;

	private String email;

	private Role role;

	private String password;

	private String firstName;

	private String lastName;

	private LocalDate birthDate;

	private String phoneNumber;

	private String address;

	private LocalDate registrationDate;

	private LocalDate lastLoginDate;

}
