package com.wolfhack.payment.listener;

import com.wolfhack.payment.model.dto.UserRegisteredNotificationDTO;
import com.wolfhack.payment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

	private final CustomerService customerService;

	@KafkaListener(topics = "user-register", groupId = "group-id", containerFactory = "userRegisterListenerContainerFactory")
	public void listenUserRegistration(UserRegisteredNotificationDTO message) {
		customerService.createCustomer(message);
	}
}
