package com.wolfhack.service;

import com.wolfhack.adapter.database.PasswordResetRequestDatabaseAdapter;
import com.wolfhack.adapter.database.UserDatabaseAdapter;
import com.wolfhack.client.EmailNotificationClient;
import com.wolfhack.model.domain.PasswordResetRequest;
import com.wolfhack.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

	private final PBKDF2Encoder pbkdf2Encoder;
	private final UserDatabaseAdapter userDatabaseAdapter;
	private final PasswordResetRequestDatabaseAdapter passwordResetRequestDatabaseAdapter;
	private final EmailNotificationClient emailNotificationClient;
	private final AnalyticsSender analyticsSender;

	public long register(User user) {
		user.register(pbkdf2Encoder);
		Long userId = userDatabaseAdapter.save(user);
		PasswordResetRequest passwordResetRequest = new PasswordResetRequest(userId);
		passwordResetRequestDatabaseAdapter.save(passwordResetRequest);
		emailNotificationClient.sendRegistrationNotification(user.getEmail());
		analyticsSender.sendRegistration(user);
		return userId;
	}

}
