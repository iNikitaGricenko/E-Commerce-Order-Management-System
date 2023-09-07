package com.wolfhack.listener;

import com.wolfhack.adapter.database.EventLogDatabaseAdapter;
import com.wolfhack.adapter.database.InventoryDatabaseAdapter;
import com.wolfhack.model.domain.EventLog;
import com.wolfhack.model.domain.ProductInventory;
import com.wolfhack.model.dto.ProductAddedDTO;
import com.wolfhack.model.dto.ProductVariantAddedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaListeners {

	private final InventoryDatabaseAdapter inventoryDatabaseAdapter;
	private final EventLogDatabaseAdapter eventLogDatabaseAdapter;

	@KafkaListener(topics = "product-added", groupId = "group-id", containerFactory = "productAddedListenerContainerFactory")
	public void listenProductAdded(ProductAddedDTO message) {
		ProductInventory productInventory = new ProductInventory();
		productInventory.create(message);

		Long inventoryId = inventoryDatabaseAdapter.save(productInventory);

		EventLog eventLog = new EventLog();
		eventLog.inventoryCreated(inventoryId, productInventory.getAvailableQuantity());

		eventLogDatabaseAdapter.save(eventLog);
	}

	@KafkaListener(topics = "product-variant-added", groupId = "group-id", containerFactory = "productVariantAddedListenerContainerFactory")
	public void listenProductVariantAdded(ProductVariantAddedDTO message) {
		Long productId = message.productId();

		ProductInventory productInventory = inventoryDatabaseAdapter.getByProductId(productId);

		productInventory.setAvailableQuantity(productInventory.getAvailableQuantity() + message.availableQuantity());
		inventoryDatabaseAdapter.partialUpdate(productInventory.getId(), productInventory);

		EventLog eventLog = new EventLog();
		eventLog.productAdded(productInventory.getId(), productInventory.getAvailableQuantity());

		eventLogDatabaseAdapter.save(eventLog);
	}

	@KafkaListener(topics = "product-removed", groupId = "group-id", containerFactory = "productRemovedListenerContainerFactory")
	public void listenProductRemovedAdded(Long productId) {
		ProductInventory productInventory = inventoryDatabaseAdapter.getByProductId(productId);

		inventoryDatabaseAdapter.delete(productInventory.getId());

		EventLog eventLog = new EventLog();
		eventLog.inventoryRemoved(productInventory.getId());

		eventLogDatabaseAdapter.save(eventLog);
	}
}
