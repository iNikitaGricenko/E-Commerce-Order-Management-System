package com.wolfhack.model.entity;

import java.time.LocalDate;

public class UserSessionEntity {

	private Long id;

	private Long userId;

	private String sessionToken;

	private LocalDate expirationDate;

}
