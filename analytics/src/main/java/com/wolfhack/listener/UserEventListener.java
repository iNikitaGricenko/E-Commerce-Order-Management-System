package com.wolfhack.listener;

import com.wolfhack.mapper.UserEventMapper;
import com.wolfhack.model.domain.User;
import com.wolfhack.model.event.DefaultEvent;
import com.wolfhack.model.event.Event;
import com.wolfhack.repository.UserEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {

	private final UserEventRepository userEventRepository;
	private final UserEventMapper userEventMapper;

	@KafkaListener(topics = "user-register", groupId = "group-id", containerFactory = "userRegisterListenerContainerFactory")
	public void listenUserRegistration(User user) {
		DefaultEvent<User> event = new DefaultEvent<>();
		event.setData(user);
		processEvent(event);
	}

	public void processEvent(Event<User> event) {
		switch (event.getType()) {
			case CREATE, UPDATE, DELETE -> userEventRepository.save(userEventMapper.toEntity(event));
			default -> {}
		}
	}

}
