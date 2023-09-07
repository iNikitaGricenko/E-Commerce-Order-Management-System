package com.wolfhack.client;

import com.wolfhack.model.dto.ProductFullResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-catalog")
public interface ProductClient {

	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ProductFullResponseDTO getProduct(@PathVariable Long id);

}
