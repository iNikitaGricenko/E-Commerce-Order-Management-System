package com.wolfhack.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Entity for {@link com.wolfhack.model.domain.PasswordResetRequest}
 */
@Entity(name = "password_reset_request")
public class PasswordResetRequestEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "request_id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "reset_token")
	private String resetToken;

	@Column(name = "expiration_date")
	private LocalDate expirationDate;

}
