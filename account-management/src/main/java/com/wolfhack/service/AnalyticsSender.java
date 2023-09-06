package com.wolfhack.service;

import com.wolfhack.model.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsSender {

	private final static String REGISTER_TOPIC = "user-register";
	private final KafkaTemplate<String, User> kafkaTemplate;

	@Async
	public void sendRegistration(User user) {
		Message<User> message = MessageBuilder.withPayload(user).setHeader(KafkaHeaders.TOPIC, REGISTER_TOPIC).build();
		kafkaTemplate.send(message);
	}

}
