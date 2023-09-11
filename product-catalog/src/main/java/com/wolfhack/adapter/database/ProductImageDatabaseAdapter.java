package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.ProductImage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductImageDatabaseAdapter extends DatabaseAdapter<ProductImage> {
	List<ProductImage> getAllByProduct(Long productId);
}
