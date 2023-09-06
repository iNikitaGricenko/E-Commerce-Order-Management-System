package com.wolfhack.model.entity;

import com.wolfhack.model.Role;
import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entity for {@link com.wolfhack.model.domain.User}
 */
@Entity(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Column(name = "registration_date")
	private LocalDate registrationDate;

	@Column(name = "last_login_date")
	private LocalDate lastLoginDate;

}
