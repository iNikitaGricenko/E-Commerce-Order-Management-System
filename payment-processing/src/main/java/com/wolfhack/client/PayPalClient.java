package com.wolfhack.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "paypal-gateway")
public interface PayPalClient {



}
