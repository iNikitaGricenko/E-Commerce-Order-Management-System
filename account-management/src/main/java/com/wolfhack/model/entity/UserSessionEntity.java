package com.wolfhack.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "user_session")
public class UserSessionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_session_id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "session_token")
	private String sessionToken;

	@Column(name = "expiration_date")
	private LocalDate expirationDate;

}
