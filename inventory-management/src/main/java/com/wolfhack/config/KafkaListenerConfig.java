package com.wolfhack.config;

import com.wolfhack.model.dto.ProductAddedDTO;
import com.wolfhack.model.dto.ProductVariantAddedDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

@Configuration
public class KafkaListenerConfig {

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProductAddedDTO> productAddedListenerContainerFactory(
			ConsumerFactory<String, ProductAddedDTO> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, ProductAddedDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProductVariantAddedDTO> productVariantListenerContainerFactory(
			ConsumerFactory<String, ProductVariantAddedDTO> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, ProductVariantAddedDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
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
