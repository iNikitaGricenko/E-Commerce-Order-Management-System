package com.wolfhack.service;

import com.wolfhack.adapter.database.ProductDatabaseAdapter;
import com.wolfhack.adapter.database.ProductReviewDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.model.domain.ProductReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductReviewService {

	private final ProductDatabaseAdapter productDatabaseAdapter;
	private final ProductReviewDatabaseAdapter productReviewDatabaseAdapter;

	public long addReview(Long productId, ProductReview productReview) {
		if (!productDatabaseAdapter.exists(productId)) {
			throw new NotFoundException("Product does not exist");
		}
		productReview.setProductId(productId);
		productReview.setReviewDate(LocalDate.now());

		return productReviewDatabaseAdapter.save(productReview);
	}

	public List<ProductReview> getByProductId(Long productId) {
		if (!productDatabaseAdapter.exists(productId)) {
			throw new NotFoundException("Product does not exist");
		}

		return productReviewDatabaseAdapter.getAllByProduct(productId);
	}

}
