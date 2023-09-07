package com.wolfhack.client;

import com.wolfhack.model.dto.ProductInventoryResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "inventory-management")
public interface InventoryClient {

	@RequestMapping(value = "/product/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	ProductInventoryResponseDTO getInventoryByProductId(@PathVariable Long productId);

}
