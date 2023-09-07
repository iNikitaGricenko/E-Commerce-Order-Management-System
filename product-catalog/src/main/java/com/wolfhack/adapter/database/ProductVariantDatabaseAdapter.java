package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.ProductVariant;

import java.util.List;

public interface ProductVariantDatabaseAdapter extends DatabaseAdapter<ProductVariant> {
	List<ProductVariant> getByProduct(Long productId);
}
