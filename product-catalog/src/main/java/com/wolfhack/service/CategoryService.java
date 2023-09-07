package com.wolfhack.service;

import com.wolfhack.adapter.database.CategoryDatabaseAdapter;
import com.wolfhack.adapter.database.ProductDatabaseAdapter;
import com.wolfhack.model.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryDatabaseAdapter categoryDatabaseAdapter;
	private final ProductDatabaseAdapter productDatabaseAdapter;

	public long addCategory(Category category) {
		return categoryDatabaseAdapter.save(category);
	}

	@Async
	public void removeCategory(Long categoryId) {
		productDatabaseAdapter.getAllByCategory(categoryId).stream()
				.peek(product -> product.setCategoryId(null))
				.forEach(product -> productDatabaseAdapter.partialUpdate(product.getId(), product));

		categoryDatabaseAdapter.delete(categoryId);
	}

}
