package com.wolfhack.service;

import com.wolfhack.adapter.database.CartItemDatabaseAdapter;
import com.wolfhack.adapter.database.UserCartDatabaseAdapter;
import com.wolfhack.client.InventoryClient;
import com.wolfhack.mapper.CartItemMapper;
import com.wolfhack.mapper.UserCartMapper;
import com.wolfhack.model.domain.UserCart;
import com.wolfhack.model.entity.CartItemResponseDTO;
import com.wolfhack.model.entity.UserCartResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCartService {

	private final UserCartDatabaseAdapter userCartDatabaseAdapter;
	private final CartItemDatabaseAdapter cartItemDatabaseAdapter;

	private final UserCartMapper userCartMapper;
	private final CartItemMapper cartItemMapper;

	private final InventoryClient inventoryClient;

	public UserCartResponseDTO getUserCart(Long userId) {
		UserCart userCart = userCartDatabaseAdapter.getByUser(userId);
		List<CartItemResponseDTO> cartItems = cartItemDatabaseAdapter.getByCartId(userCart.getId()).stream()
				.map(cartItemMapper::toResponse).toList();
		UserCartResponseDTO response = userCartMapper.toResponse(userCart);
		response.setCartItems(cartItems);

		return response;
	}

	// TODO add products from inventory to cart. Only if available in inventory

}
