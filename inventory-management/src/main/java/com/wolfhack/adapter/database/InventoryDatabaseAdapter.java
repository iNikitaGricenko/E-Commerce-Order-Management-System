package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.ProductInventory;

public interface InventoryDatabaseAdapter extends DatabaseAdapter<ProductInventory> {
	ProductInventory getByProductId(Long productId);
}
