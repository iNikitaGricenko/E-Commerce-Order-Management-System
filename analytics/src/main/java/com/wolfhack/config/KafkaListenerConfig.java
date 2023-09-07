package com.wolfhack.config;

import com.wolfhack.model.ProductAddedDTO;
import com.wolfhack.model.UserRegisteredNotificationDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaListenerConfig {

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserRegisteredNotificationDTO> userRegisterListenerContainerFactory(
			ConsumerFactory<String, UserRegisteredNotificationDTO> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, UserRegisteredNotificationDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProductAddedDTO> productAddedListenerContainerFactory(
			ConsumerFactory<String, ProductAddedDTO> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, ProductAddedDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Long> productRemovedListenerContainerFactory(
			ConsumerFactory<String, Long> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, Long> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

}
