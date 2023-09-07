package com.wolfhack.service;

import com.wolfhack.adapter.database.*;
import com.wolfhack.config.KafkaTopics;
import com.wolfhack.model.domain.*;
import com.wolfhack.model.dto.ProductAddedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductDatabaseAdapter productDatabaseAdapter;
	private final ProductImageDatabaseAdapter productImageDatabaseAdapter;
	private final ProductReviewDatabaseAdapter productReviewDatabaseAdapter;
	private final ProductVariantDatabaseAdapter productVariantDatabaseAdapter;
	private final RelatedProductDatabaseAdapter relatedProductDatabaseAdapter;

	private final CategoryDatabaseAdapter categoryDatabaseAdapter;

	private final Map<String, KafkaTopics> kafkaTopics;

	private final KafkaTemplate<String, ProductAddedDTO> kafkaAddedTemplate;
	private final KafkaTemplate<String, Long> kafkaRemovedTemplate;

	public long addProduct(Product product) {
		Long productId = productDatabaseAdapter.save(product);
		try {
			return productId;
		} finally {
			addToInventory(product, productId);
		}
	}

	public Product get(Long productId) {
		return productDatabaseAdapter.getById(productId);
	}

	public void assignCategory(Long productId, Long categoryId) {
		if (!productDatabaseAdapter.exists(productId)) {
			throw new RuntimeException("Product not found");
		}
		if (!categoryDatabaseAdapter.exists(categoryId)) {
			throw new RuntimeException("Category not found");
		}

		Product product = new Product();
		product.setCategoryId(categoryId);

		productDatabaseAdapter.partialUpdate(productId, product);
	}

	public void addRelatedProduct(Long productId, List<Long> relatedProductIds) {
		if (!productDatabaseAdapter.exists(productId)) {
			throw new RuntimeException("Product not found");
		}

		relatedProductIds.stream().filter(productDatabaseAdapter::exists).map(id -> {
			RelatedProduct relatedProduct = new RelatedProduct();
			relatedProduct.setProductId(productId);
			relatedProduct.setRelatedProductId(id);
			return relatedProduct;
		}).forEach(relatedProductDatabaseAdapter::save);
	}

	public void removeProduct(Long productId) {
		productDatabaseAdapter.delete(productId);
		EventLog eventLog = new EventLog();
		eventLog.productRemoved(productId);

		kafkaRemovedTemplate.send(kafkaTopics.get("removed").topic(), productId);
	}

	@Async
	protected void addToInventory(Product product, Long productId) {
		EventLog eventLog = new EventLog();
		eventLog.productCreated(productId, product.getStockQuantity());

		ProductAddedDTO data = new ProductAddedDTO(productId, product.getName(), product.getStockQuantity());
		kafkaAddedTemplate.send(kafkaTopics.get("added").topic(), data);
	}
}
