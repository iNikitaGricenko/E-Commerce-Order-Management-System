package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.ProductImage;

import java.util.List;

public interface ProductImageDatabaseAdapter extends DatabaseAdapter<ProductImage> {
	List<ProductImage> getAllByProduct(Long productId);
}
