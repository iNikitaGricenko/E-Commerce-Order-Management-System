package com.wolfhack.service;

import com.wolfhack.adapter.database.EventLogDatabaseAdapter;
import com.wolfhack.adapter.database.ProductDatabaseAdapter;
import com.wolfhack.adapter.database.ProductVariantDatabaseAdapter;
import com.wolfhack.config.KafkaTopics;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.model.domain.EventLog;
import com.wolfhack.model.domain.Product;
import com.wolfhack.model.domain.ProductVariant;
import com.wolfhack.model.dto.ProductVariantAddedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductVariantService {

	private final ProductDatabaseAdapter productDatabaseAdapter;
	private final ProductVariantDatabaseAdapter productVariantDatabaseAdapter;
	private final EventLogDatabaseAdapter eventLogDatabaseAdapter;

	private final Map<String, KafkaTopics> kafkaTopics;
	private final KafkaTemplate<String, ProductVariantAddedDTO> kafkaVariantAddedTemplate;

	public long addVariant(Long productId, ProductVariant productVariant) {
		if (!productDatabaseAdapter.exists(productId)) {
			throw new NotFoundException("Product does not exist");
		}
		productVariant.setProductId(productId);

		Long productVariantId = productVariantDatabaseAdapter.save(productVariant);
		try {
			return productVariantId;
		} finally {
			addToInventory(productId, productVariant, productVariantId);
		}
	}

	public List<ProductVariant> getByProductId(Long productId) {
		if (!productDatabaseAdapter.exists(productId)) {
			throw new NotFoundException("Product does not exist");
		}

		return productVariantDatabaseAdapter.getByProduct(productId);
	}

	@Async
	protected void addToInventory(Long productId, ProductVariant productVariant, Long productVariantId) {
		Product product = productDatabaseAdapter.getById(productId);
		product.setStockQuantity(product.getStockQuantity() + productVariant.getVariantStockQuantity());
		productDatabaseAdapter.partialUpdate(productId, product);

		EventLog eventLog = new EventLog();
		eventLog.productVariantCreated(productId, product.getStockQuantity());

		eventLogDatabaseAdapter.save(eventLog);

		ProductVariantAddedDTO data = new ProductVariantAddedDTO(productVariantId, productId, product.getName(), product.getStockQuantity());
		kafkaVariantAddedTemplate.send(kafkaTopics.get("variant-added").topic(), data);
	}

}
