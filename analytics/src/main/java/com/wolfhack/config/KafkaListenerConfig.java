package com.wolfhack.config;

import com.wolfhack.model.dto.ProductAdded;
import com.wolfhack.model.dto.UserRegistered;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaListenerConfig {

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserRegistered> userRegisterListenerContainerFactory(
			ConsumerFactory<String, UserRegistered> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, UserRegistered> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProductAdded> productAddedListenerContainerFactory(
			ConsumerFactory<String, ProductAdded> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, ProductAdded> factory = new ConcurrentKafkaListenerContainerFactory<>();
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
