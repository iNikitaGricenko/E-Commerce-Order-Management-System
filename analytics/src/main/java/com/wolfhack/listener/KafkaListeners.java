package com.wolfhack.listener;

import com.wolfhack.model.UserRegisteredNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

	@KafkaListener(topics = "user-register", groupId = "group-id", containerFactory = "userRegisterListenerContainerFactory")
	public void listenUserRegistration(UserRegisteredNotificationDTO message) {

	}
}
