package com.wolfhack.cloud.client;

import com.wolfhack.cloud.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import reactor.core.publisher.Mono;

public interface AccountManagementClient {

	Mono<User> findByEmail(String subject);

	Mono<Long> saveUser(Mono<User> map);

}
