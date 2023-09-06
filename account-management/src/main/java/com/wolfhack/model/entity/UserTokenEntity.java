package com.wolfhack.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "user_token")
public class UserTokenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_token_id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "token")
	private String token;

	@Column(name = "expiration_date")
	private LocalDate expirationDate;

}
