package com.wolfhack.service;

import com.wolfhack.adapter.database.UserCartDatabaseAdapter;
import com.wolfhack.model.domain.UserCart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

	private final UserCartDatabaseAdapter userCartDatabaseAdapter;

	public UserCart getCart(Long id) {
		return userCartDatabaseAdapter.getById(id);
	}

}
