package com.wolfhack.cloud.client;

import com.wolfhack.cloud.model.User;
import com.wolfhack.cloud.model.dto.UserLogin;
import com.wolfhack.cloud.model.dto.UserRegistration;
import com.wolfhack.cloud.model.dto.UserTokenResponseDTO;
import reactor.core.publisher.Mono;

public interface AccountManagementClient {

	Mono<User> findByUsername(String subject);

	Mono<Long> register(Mono<UserRegistration> map);

	Mono<Boolean> login(UserLogin userLogin);

	Mono<UserTokenResponseDTO> findByToken(String token);

	Mono<User> findById(Long id);
}
