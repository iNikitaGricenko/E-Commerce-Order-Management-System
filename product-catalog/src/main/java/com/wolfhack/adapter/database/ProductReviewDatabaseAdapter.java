package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.ProductReview;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductReviewDatabaseAdapter extends DatabaseAdapter<ProductReview> {
	List<ProductReview> getAllByProduct(Long productId);
}
