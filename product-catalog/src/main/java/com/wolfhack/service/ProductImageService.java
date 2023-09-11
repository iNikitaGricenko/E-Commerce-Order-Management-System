package com.wolfhack.service;

import com.wolfhack.adapter.database.ProductDatabaseAdapter;
import com.wolfhack.adapter.database.ProductImageDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.logging.annotations.AOPLogging;
import com.wolfhack.model.domain.ProductImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageService {

	private final ProductDatabaseAdapter productDatabaseAdapter;
	private final ProductImageDatabaseAdapter productImageDatabaseAdapter;

	public long addImage(Long productId, ProductImage productImage) {
		if (!productDatabaseAdapter.exists(productId)) {
			throw new NotFoundException("Product does not exist");
		}
		productImage.setProductId(productId);
		return productImageDatabaseAdapter.save(productImage);
	}

	@AOPLogging
	public List<ProductImage> getByProductId(Long productId) {
		if (!productDatabaseAdapter.exists(productId)) {
			throw new NotFoundException("Product does not exist");
		}

		return productImageDatabaseAdapter.getAllByProduct(productId);
	}

}
