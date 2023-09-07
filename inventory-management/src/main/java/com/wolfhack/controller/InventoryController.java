package com.wolfhack.controller;

import com.wolfhack.model.dto.ProductInventoryResponseDTO;
import com.wolfhack.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;

	@GetMapping("/{id}")
	public ProductInventoryResponseDTO get(@PathVariable Long id) {
		return inventoryService.getInventory(id);
	}

	@GetMapping("/product/{productId}")
	public ProductInventoryResponseDTO getByProduct(@PathVariable Long productId) {
		return inventoryService.getInventoryByProduct(productId);
	}

}
