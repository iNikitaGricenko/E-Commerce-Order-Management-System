package com.wolfhack.payment.config;

import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PaypalConfiguration {

    @Bean
    @Profile({"dev", "default"})
    public PayPalHttpClient getSandboxPaypalClient(@Value("${paypal.client.id") String clientId,
                                                   @Value("${paypal.client.secret") String clientSecret) {
        return new PayPalHttpClient(new PayPalEnvironment.Sandbox(clientId, clientSecret));
    }

    @Bean
    @Profile({"production"})
    public PayPalHttpClient getLivePaypalClient(@Value("${paypal.client.id") String clientId,
                                            @Value("${paypal.client.secret") String clientSecret) {
        return new PayPalHttpClient(new PayPalEnvironment.Live(clientId, clientSecret));
    }

}
