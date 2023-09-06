package com.wolfhack.model.entity;

import java.time.LocalDate;

public class UserTokenEntity {

	private Long id;

	private Long userId;

	private String token;

	private LocalDate expirationDate;

}
