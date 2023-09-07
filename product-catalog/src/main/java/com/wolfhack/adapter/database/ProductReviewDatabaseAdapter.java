package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.ProductReview;

import java.util.List;

public interface ProductReviewDatabaseAdapter extends DatabaseAdapter<ProductReview> {
	List<ProductReview> getAllByProduct(Long productId);
}
