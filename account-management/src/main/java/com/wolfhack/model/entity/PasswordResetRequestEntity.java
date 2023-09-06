package com.wolfhack.model.entity;

import java.time.LocalDate;

public class PasswordResetRequestEntity {

	private Long id;

	private Long userId;

	private String resetToken;

	private LocalDate expirationDate;

}
