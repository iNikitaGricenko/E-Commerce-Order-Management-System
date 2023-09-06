package com.wolfhack.service;

import com.wolfhack.adapter.database.PasswordResetRequestDatabaseAdapter;
import com.wolfhack.adapter.database.UserDatabaseAdapter;
import com.wolfhack.model.domain.PasswordResetRequest;
import com.wolfhack.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PasswordManagement {

	private final PBKDF2Encoder encoder;
	private final UserDatabaseAdapter userDatabaseAdapter;
	private final PasswordResetRequestDatabaseAdapter passwordResetRequestDatabaseAdapter;

	public void resetPassword(String token, Long userId) {
		PasswordResetRequest resetRequest = passwordResetRequestDatabaseAdapter.getByToken(token);
		if (!Objects.equals(resetRequest.getUserId(), userId)) {
			throw new RuntimeException();
		}
	}

	public void changePassword(Long userId, String password) {
		User user = userDatabaseAdapter.getById(userId);
		String newPassword = encoder.encode(password);

		if (newPassword.equals(user.getPassword())) {
			throw new RuntimeException("New password couldn't be same as previous one");
		}

		user.setPassword(newPassword);
		userDatabaseAdapter.partialUpdate(userId, user);
	}

}
