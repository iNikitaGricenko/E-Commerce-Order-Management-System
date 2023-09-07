package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.CartItem;

import java.util.List;

public interface CartItemDatabaseAdapter extends DatabaseAdapter<CartItem> {
	List<CartItem> getByCartId(Long cartId);
}
