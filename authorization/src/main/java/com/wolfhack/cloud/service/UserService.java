package com.wolfhack.cloud.service;

import com.wolfhack.cloud.client.AccountManagementClient;
import com.wolfhack.cloud.model.Role;
import com.wolfhack.cloud.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

	private final AccountManagementClient accountManagementClient;

	public Mono<Long> save(Mono<User> user) {
		return accountManagementClient.saveUser(
				user.map(it -> {
					it.setRole(Role.USER);
					it.setRegisteredAt(LocalDate.now());
					return it;
				}));
	}

	public Mono<User> get(String email) {
		return accountManagementClient.findByEmail(email);
	}

}
