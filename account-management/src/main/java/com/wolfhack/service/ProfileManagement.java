package com.wolfhack.service;

import com.wolfhack.adapter.database.UserDatabaseAdapter;
import com.wolfhack.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileManagement {

	private final PBKDF2Encoder encoder;
	private final UserDatabaseAdapter userDatabaseGateway;

	public boolean validate(String username, String password) {
		User user = userDatabaseGateway.getByUsername(username);
		String encoded = encoder.encode(password);

		if (!user.getPassword().equals(encoded)) {
			throw new RuntimeException("Invalid password");
		}

		return true;
	}

	public void update(Long userId, User user) {
		if (!userDatabaseGateway.exists(userId)) {
			throw new RuntimeException("User does not exist");
		}

		userDatabaseGateway.partialUpdate(userId, user);
	}

}
