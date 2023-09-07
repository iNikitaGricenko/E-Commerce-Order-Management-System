package com.wolfhack.cloud.config;

import com.wolfhack.cloud.model.UserRegisteredNotificationDTO;
import com.wolfhack.cloud.model.UserResetNotificationDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaListenerConfig {

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserRegisteredNotificationDTO> userRegisterKafkaListenerContainerFactory(
			ConsumerFactory<String, UserRegisteredNotificationDTO> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, UserRegisteredNotificationDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserResetNotificationDTO> userResetKafkaListenerContainerFactory(
			ConsumerFactory<String, UserResetNotificationDTO> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, UserResetNotificationDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

}
