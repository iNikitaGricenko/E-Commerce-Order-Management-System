package com.wolfhack.service;

import com.wolfhack.adapter.database.InventoryDatabaseAdapter;
import com.wolfhack.client.ProductClient;
import com.wolfhack.mapper.ProductInventoryMapper;
import com.wolfhack.model.domain.ProductInventory;
import com.wolfhack.model.dto.ProductFullResponseDTO;
import com.wolfhack.model.dto.ProductInventoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryDatabaseAdapter inventoryDatabaseAdapter;
	private final ProductInventoryMapper productInventoryMapper;
	private final ProductClient productClient;

	public ProductInventoryResponseDTO getInventory(Long inventoryId) {
		ProductInventory productInventory = inventoryDatabaseAdapter.getById(inventoryId);
		ProductFullResponseDTO product = productClient.getProduct(productInventory.getProductId());

		ProductInventoryResponseDTO response = productInventoryMapper.toResponse(productInventory);
		response.setProduct(product);
		return response;
	}

	public ProductInventoryResponseDTO getInventoryByProduct(Long productId) {
		ProductInventory productInventory = inventoryDatabaseAdapter.getByProductId(productId);
		ProductFullResponseDTO product = productClient.getProduct(productInventory.getProductId());

		ProductInventoryResponseDTO response = productInventoryMapper.toResponse(productInventory);
		response.setProduct(product);
		return response;
	}
}
