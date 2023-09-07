package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.Product;

import java.util.List;

public interface ProductDatabaseAdapter extends DatabaseAdapter<Product> {
	List<Product> getAllByCategory(Long categoryId);
}
