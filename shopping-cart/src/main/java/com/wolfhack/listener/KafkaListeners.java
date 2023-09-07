package com.wolfhack.listener;

import com.wolfhack.adapter.database.UserCartDatabaseAdapter;
import com.wolfhack.model.domain.UserCart;
import com.wolfhack.model.dto.UserRegisteredNotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

	private final UserCartDatabaseAdapter userCartDatabaseAdapter;

	@KafkaListener(topics = "user-register", groupId = "group-id", containerFactory = "userRegisterListenerContainerFactory")
	public void listenUserRegistration(UserRegisteredNotificationDTO message) {
		UserCart userCart = new UserCart();
		userCart.setUserId(message.id());
		userCart.setCreatedDate(LocalDate.now());

		userCartDatabaseAdapter.save(userCart);
	}

}
