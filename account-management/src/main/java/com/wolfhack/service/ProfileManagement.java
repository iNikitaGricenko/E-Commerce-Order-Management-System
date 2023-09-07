package com.wolfhack.service;

import com.wolfhack.adapter.database.UserDatabaseAdapter;
import com.wolfhack.adapter.database.UserTokenDatabaseAdapter;
import com.wolfhack.model.domain.User;
import com.wolfhack.model.domain.UserToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProfileManagement {

	private final PBKDF2Encoder encoder;
	private final UserDatabaseAdapter userDatabaseGateway;
	private final UserTokenDatabaseAdapter userTokenDatabaseAdapter;

	public void login(String username, String password) {
		User user = userDatabaseGateway.getByUsername(username);

		if (!encoder.matches(user.getPassword(), password)) {
			throw new RuntimeException("Invalid password"); // TODO throw Forbidden
		}
	}

	public UserToken login(String token) {
		return userTokenDatabaseAdapter.getByToken(token);
	}

	public void setToken(Long userId, String token) {
		if (userTokenDatabaseAdapter.existsByUserId(userId)) {
			UserToken userToken = userTokenDatabaseAdapter.getByUserId(userId);
			userToken.setToken(token);
			userToken.setExpirationDate(LocalDate.now().plusYears(1));
			userTokenDatabaseAdapter.partialUpdate(userToken.getId(), userToken);
			return;
		}

		UserToken userToken = new UserToken();
		userToken.setUserId(userId);
		userToken.setToken(token);
		userToken.setExpirationDate(LocalDate.now().plusYears(1));
		userTokenDatabaseAdapter.save(userToken);
	}

	public void update(Long userId, User user) {
		if (!userDatabaseGateway.exists(userId)) {
			throw new RuntimeException("User does not exist");
		}
		user.setPassword(encoder.encode(user.getPassword()));

		userDatabaseGateway.partialUpdate(userId, user);
	}

	public User getByUsername(String username) {
		return userDatabaseGateway.getByUsername(username);
	}
}
