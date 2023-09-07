package com.wolfhack.cloud.listeners;

import com.wolfhack.cloud.model.MailMessagePOJO;
import com.wolfhack.cloud.model.UserRegisteredNotificationDTO;
import com.wolfhack.cloud.model.UserResetNotificationDTO;
import com.wolfhack.cloud.service.GoogleEmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessagingListener {

	private final GoogleEmailSender googleEmailSender;

	@KafkaListener(topics = "user-register", groupId = "group-id", containerFactory = "userRegisterKafkaListenerContainerFactory")
	public void listenSingleMessage(UserRegisteredNotificationDTO message) {
		String subject = "We are glad, that you are here";
		String body = "%s Welcome to our e-commerce site".formatted(message.firstName());

		MailMessagePOJO mailMessagePOJO = new MailMessagePOJO(message.email(), subject, body);

		googleEmailSender.send(mailMessagePOJO);
	}

	@KafkaListener(topics = "user-reset", groupId = "group-id", containerFactory = "userResetKafkaListenerContainerFactory")
	public void listenMultipleMessage(UserResetNotificationDTO message) {
		String subject = "Password reset request";
		String body = "%s Go directly by this link and insert your new password localhost:8080/account-management/password/reset/%s"
				.formatted(message.username(), message.resetToken());

		MailMessagePOJO mailMessagePOJO = new MailMessagePOJO(message.email(), subject, body);

		googleEmailSender.send(mailMessagePOJO);
	}
}
