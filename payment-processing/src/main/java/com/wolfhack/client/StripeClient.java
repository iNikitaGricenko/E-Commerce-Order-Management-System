package com.wolfhack.client;

import com.wolfhack.adapter.feign.PaymentClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "stripe-gateway")
public interface StripeClient extends PaymentClient {



}
