package com.wolfhack.listener;

import com.wolfhack.mapper.ProductEventMapper;
import com.wolfhack.model.domain.Product;
import com.wolfhack.model.event.DefaultEvent;
import com.wolfhack.model.event.Event;
import com.wolfhack.model.event.EventType;
import com.wolfhack.repository.ProductEventRepository;
import com.wolfhack.repository.UserEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventListener {

	ProductEventRepository productEventRepository;
	ProductEventMapper productEventMapper;

	@KafkaListener(topics = "product-added", groupId = "group-id", containerFactory = "productAddedListenerContainerFactory")
	public void listenProductAdded(Product product) {
		DefaultEvent<Product> event = new DefaultEvent<>();
		event.setData(product);
		processEvent(event);
	}

	@KafkaListener(topics = "product-removed", groupId = "group-id", containerFactory = "productRemovedListenerContainerFactory")
	public void listenProductRemovedAdded(Long productId) {
		DefaultEvent<Product> event = new DefaultEvent<>();
		event.setDataId(productId);
		event.setType(EventType.DELETE);
		processEvent(event);
	}

	public void processEvent(Event<Product> event) {
		switch (event.getType()) {
			case CREATE, UPDATE, DELETE -> productEventRepository.save(productEventMapper.toEntity(event));
            default -> {}
		}
	}
}
