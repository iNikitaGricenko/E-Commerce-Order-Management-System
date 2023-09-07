package com.wolfhack.controller;

import com.wolfhack.mapper.ProductReviewMapper;
import com.wolfhack.model.domain.ProductReview;
import com.wolfhack.model.dto.ProductReviewCreationDTO;
import com.wolfhack.model.dto.ProductReviewResponseDTO;
import com.wolfhack.service.ProductReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review/{productId}")
@RequiredArgsConstructor
public class ReviewController {

	private final ProductReviewService productReviewService;
	private final ProductReviewMapper productReviewMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public long createReview(@PathVariable Long productId, @RequestBody @Valid ProductReviewCreationDTO productReviewCreationDTO) {
		ProductReview model = productReviewMapper.toModel(productReviewCreationDTO);
		return productReviewService.addReview(productId, model);
	}

	@GetMapping
	public List<ProductReviewResponseDTO> getReviews(@PathVariable Long productId) {
		List<ProductReview> reviews = productReviewService.getByProductId(productId);
		return reviews.stream().map(productReviewMapper::toResponse).toList();
	}

}
