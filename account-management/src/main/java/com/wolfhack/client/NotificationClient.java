package com.wolfhack.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "google-notification")
public interface NotificationClient {
}
