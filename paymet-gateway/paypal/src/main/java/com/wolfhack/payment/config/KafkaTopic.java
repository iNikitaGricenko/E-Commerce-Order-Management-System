package com.wolfhack.payment.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopic {

	@Value(value = "${spring.kafka.bootstrap-servers}") private String bootstrapAddress;

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new KafkaAdmin(configs);
	}

	@Bean
	public NewTopic topic(Map<String, KafkaTopics> kafkaTopics) {
		final String topic = "";
		kafkaTopics.put("", new KafkaTopics(topic));
		return TopicBuilder.name(topic).build();
	}

	@Bean
	public Map<String, KafkaTopics> kafkaTopics() {
		return Map.of("default", new KafkaTopics("default"));
	}


}