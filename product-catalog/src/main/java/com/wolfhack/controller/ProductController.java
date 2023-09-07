package com.wolfhack.controller;

import com.wolfhack.mapper.ProductMapper;
import com.wolfhack.model.domain.Category;
import com.wolfhack.model.domain.Product;
import com.wolfhack.model.dto.ProductCreationDTO;
import com.wolfhack.model.dto.ProductFullResponseDTO;
import com.wolfhack.service.CategoryService;
import com.wolfhack.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	private final CategoryService categoryService;
	private final ProductMapper productMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public long add(@RequestBody @Valid ProductCreationDTO productCreationDTO) {
		Product model = productMapper.toModel(productCreationDTO);
		return productService.addProduct(model);
	}

	@GetMapping("/{id}")
	public ProductFullResponseDTO get(@PathVariable Long id) {
		Product product = productService.get(id);

		ProductFullResponseDTO response = productMapper.toFullResponse(product);

		Category category = categoryService.get(product.getCategoryId());
		response.setCategoryId(category.getId());
		response.setCategoryName(category.getName());

		return response;
	}

	@PostMapping("/{productId}/{categoryId}")
	public void assignCategory(@PathVariable Long productId, @PathVariable Long categoryId) {
		productService.assignCategory(productId, categoryId);
	}

	@PostMapping("/{productId}")
	public void addRelatedProducts(@PathVariable Long productId, @RequestBody List<Long> relatedProducts) {
		productService.addRelatedProduct(productId, relatedProducts);
	}

	@DeleteMapping("/{productId}")
	public void delete(@PathVariable Long productId) {
		productService.removeProduct(productId);
	}
}
