package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.ProductVariant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductVariantDatabaseAdapter extends DatabaseAdapter<ProductVariant> {
	List<ProductVariant> getByProduct(Long productId);
}
