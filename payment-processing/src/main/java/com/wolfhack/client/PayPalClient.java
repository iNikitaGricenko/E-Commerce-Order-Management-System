package com.wolfhack.client;

import com.wolfhack.adapter.feign.PaymentClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "paypal-gateway")
public interface PayPalClient extends PaymentClient {



}
