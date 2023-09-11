package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDatabaseAdapter extends DatabaseAdapter<Product> {
	List<Product> getAllByCategory(Long categoryId);
}
