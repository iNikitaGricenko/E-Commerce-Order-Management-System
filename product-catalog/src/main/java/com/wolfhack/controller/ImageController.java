package com.wolfhack.controller;

import com.wolfhack.mapper.ProductImageMapper;
import com.wolfhack.model.domain.ProductImage;
import com.wolfhack.model.dto.ProductImageCreationDTO;
import com.wolfhack.model.dto.ProductImageResponseDTO;
import com.wolfhack.service.ProductImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/image/{productId}")
@RequiredArgsConstructor
public class ImageController {

	private final ProductImageService productImageService;
	private final ProductImageMapper productImageMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public long add(@PathVariable Long productId, @RequestBody @Valid ProductImageCreationDTO productImageCreationDTO) {
		ProductImage model = productImageMapper.toModel(productImageCreationDTO);
		return productImageService.addImage(productId, model);
	}

	@GetMapping
	public List<ProductImageResponseDTO> get(@PathVariable Long productId) {
		List<ProductImage> productImages = productImageService.getByProductId(productId);
		return productImages.stream().map(productImageMapper::toResponse).toList();
	}

}
