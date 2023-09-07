package com.wolfhack.listener;

import com.wolfhack.model.dto.ProductAddedDTO;
import com.wolfhack.model.dto.ProductVariantAddedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

	@KafkaListener(topics = "product-added", groupId = "group-id", containerFactory = "productAddedListenerContainerFactory")
	public void listenProductAdded(ProductAddedDTO message) {

	}

	@KafkaListener(topics = "product-variant-added", groupId = "group-id", containerFactory = "productVariantAddedListenerContainerFactory")
	public void listenProductVariantAdded(ProductVariantAddedDTO message) {

	}

	@KafkaListener(topics = "product-removed", groupId = "group-id", containerFactory = "productRemovedListenerContainerFactory")
	public void listenProductVariantAdded(Long message) {

	}
}
