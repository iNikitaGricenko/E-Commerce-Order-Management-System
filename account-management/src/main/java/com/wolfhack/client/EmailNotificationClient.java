package com.wolfhack.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "email-notification")
public interface EmailNotificationClient {

	void sendRegistrationNotification(@RequestParam String email);

}
