package com.wolfhack.controller;

import com.wolfhack.mapper.ProductVariantMapper;
import com.wolfhack.model.domain.ProductVariant;
import com.wolfhack.model.dto.ProductVariantCreationDTO;
import com.wolfhack.model.dto.ProductVariantResponseDTO;
import com.wolfhack.service.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variant/{productId}")
@RequiredArgsConstructor
public class VariantController {

	private final ProductVariantService productVariantService;
	private final ProductVariantMapper productVariantMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public long addVariant(@PathVariable Long productId,
	                       @RequestBody @Valid ProductVariantCreationDTO productVariantCreationDTO) {
		ProductVariant model = productVariantMapper.toModel(productVariantCreationDTO);
		return productVariantService.addVariant(productId, model);
	}

	@GetMapping
	public List<ProductVariantResponseDTO> getVariants(@PathVariable Long productId) {
		List<ProductVariant> variants = productVariantService.getByProductId(productId);
		return variants.stream().map(productVariantMapper::toResponse).toList();
	}

}
