package com.wolfhack.cloud.client;

import com.wolfhack.cloud.model.User;
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
	public Mono<User> findByEmail(String email) {
		return webClient.build()
				.get()
				.uri(uriBuilder -> uriBuilder.host("account-management").path("/account").queryParam("email", email).build())
				.retrieve()
				.onStatus(HttpStatus.NOT_FOUND::equals, response -> Mono.empty())
				.bodyToMono(User.class)
				.onErrorStop();
	}

	@Override
	public Mono<Long> saveUser(Mono<User> userMono) {
		return webClient.build()
				.post()
				.uri(uriBuilder -> uriBuilder.host("account-management").path("/account").build())
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(userMono))
				.retrieve().bodyToMono(Long.class);
	}
}
