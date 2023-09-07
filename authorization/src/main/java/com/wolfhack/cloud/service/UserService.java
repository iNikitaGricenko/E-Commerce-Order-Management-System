package com.wolfhack.cloud.service;

import com.wolfhack.cloud.client.AccountManagementClient;
import com.wolfhack.cloud.model.User;
import com.wolfhack.cloud.model.dto.UserRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

	private final AccountManagementClient accountManagementClient;

	public Mono<Long> save(Mono<UserRegistration> user) {
		return accountManagementClient.register(user);
	}

	public Mono<User> get(String username) {
		return accountManagementClient.findByUsername(username);
	}

}
