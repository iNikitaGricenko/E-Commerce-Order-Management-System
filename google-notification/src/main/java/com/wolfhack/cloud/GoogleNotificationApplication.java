package com.wolfhack.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class GoogleNotificationApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoogleNotificationApplication.class, args);
	}
}