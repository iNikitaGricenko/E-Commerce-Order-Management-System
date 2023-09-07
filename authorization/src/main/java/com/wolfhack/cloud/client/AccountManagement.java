package com.wolfhack.cloud.client;

import com.wolfhack.cloud.exception.UnauthorizedException;
import com.wolfhack.cloud.model.User;
import com.wolfhack.cloud.model.dto.UserLogin;
import com.wolfhack.cloud.model.dto.UserRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountManagement implements AccountManagementClient {

	private final WebClient.Builder webClient;

	@Override
	public Mono<User> findByUsername(String username) {
		return webClient.build()
				.get()
				.uri(uriBuilder -> uriBuilder.host("account-management").path("/profile").queryParam("username", username).build())
				.retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
				.bodyToMono(User.class)
				.onErrorStop();
	}

	@Override
	public Mono<Long> register(Mono<UserRegistration> userMono) {
		return webClient.build()
				.post()
				.uri(uriBuilder -> uriBuilder.host("account-management").path("/register").build())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(userMono))
				.retrieve().bodyToMono(Long.class);
	}

	@Override
	public Mono<Boolean> login(UserLogin userLogin) {
		return webClient.build()
				.post()
				.uri(uriBuilder -> uriBuilder.host("account-management").path("/profile/login").build())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(userLogin))
				.retrieve()
				.onStatus(HttpStatus.FORBIDDEN::equals, response -> Mono.error(new UnauthorizedException("Token is not valid")))
				.bodyToMono(boolean.class);
	}
}
