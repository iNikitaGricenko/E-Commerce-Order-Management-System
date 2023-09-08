package com.wolfhack.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-management")
public interface UserClient {

	@GetMapping("/{userId}")
	void exists(@PathVariable Long userId);

}
