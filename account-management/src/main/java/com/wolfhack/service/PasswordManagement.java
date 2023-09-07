package com.wolfhack.service;

import com.wolfhack.adapter.database.PasswordResetRequestDatabaseAdapter;
import com.wolfhack.adapter.database.UserDatabaseAdapter;
import com.wolfhack.model.domain.PasswordResetRequest;
import com.wolfhack.model.domain.User;
import com.wolfhack.model.dto.UserResetNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordManagement {

	private final PBKDF2Encoder encoder;
	private final NotificationSender notificationSender;
	private final UserDatabaseAdapter userDatabaseAdapter;
	private final PasswordResetRequestDatabaseAdapter passwordResetRequestDatabaseAdapter;

	public void resetPasswordRequest(String email) {
		User user = userDatabaseAdapter.getByEmail(email);
		PasswordResetRequest resetRequest = passwordResetRequestDatabaseAdapter.getByUserId(user.getId());
		String resetToken = resetRequest.getResetToken();

		UserResetNotificationDTO notificationDTO = new UserResetNotificationDTO(
				user.getId(), user.getEmail(), user.getUsername(), resetToken);

		notificationSender.sendReset(notificationDTO);
	}

	public void changePassword(String token, String password) {
		PasswordResetRequest resetRequest = passwordResetRequestDatabaseAdapter.getByToken(token);
		Long userId = resetRequest.getUserId();
		User user = userDatabaseAdapter.getById(userId);
		String newPassword = encoder.encode(password);

		if (newPassword.equals(user.getPassword())) {
			throw new RuntimeException("New password couldn't be same as previous one"); // Send Forbbiden exception
		}

		user.setPassword(newPassword);
		userDatabaseAdapter.partialUpdate(userId, user);
	}

}
