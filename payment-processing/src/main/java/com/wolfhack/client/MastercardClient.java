package com.wolfhack.client;

import com.wolfhack.adapter.feign.PaymentClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mastercard-gateway")
public interface MastercardClient extends PaymentClient {



}
