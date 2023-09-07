package com.wolfhack.listener;

import com.wolfhack.model.ProductAddedDTO;
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

	@KafkaListener(topics = "product-added", groupId = "group-id", containerFactory = "productAddedListenerContainerFactory")
	public void listenProductAdded(ProductAddedDTO message) {

	}

	@KafkaListener(topics = "product-removed", groupId = "group-id", containerFactory = "productRemovedListenerContainerFactory")
	public void listenProductRemovedAdded(Long productId) {

	}
}
