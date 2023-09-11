package com.wolfhack.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "stripe-gateway")
public interface StripeClient {



}
