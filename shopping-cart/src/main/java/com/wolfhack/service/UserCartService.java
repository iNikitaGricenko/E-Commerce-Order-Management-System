package com.wolfhack.service;

import com.wolfhack.adapter.database.CartItemDatabaseAdapter;
import com.wolfhack.adapter.database.UserCartDatabaseAdapter;
import com.wolfhack.client.InventoryClient;
import com.wolfhack.client.UserClient;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.CartItemMapper;
import com.wolfhack.mapper.UserCartMapper;
import com.wolfhack.model.domain.CartItem;
import com.wolfhack.model.domain.UserCart;
import com.wolfhack.model.dto.ProductInventoryResponseDTO;
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
	private final UserClient userClient;

	public UserCartResponseDTO getUserCart(Long userId) {
		UserCart userCart = userCartDatabaseAdapter.getByUser(userId);
		List<CartItemResponseDTO> cartItems = cartItemDatabaseAdapter.getByCartId(userCart.getId()).stream()
				.map(cartItemMapper::toResponse).toList();
		UserCartResponseDTO response = userCartMapper.toResponse(userCart);
		response.setCartItems(cartItems);

		return response;
	}

	public long addProduct(Long userId, CartItem model) {
		UserCart userCart = userCartDatabaseAdapter.getByUser(userId);
		ProductInventoryResponseDTO inventory = inventoryClient.getInventoryByProductId(model.getProductId());

		if (inventory == null) {
			throw new NotFoundException("Product does not exist or not available");
		}

		CartItem cartItem = new CartItem();
		cartItem.setCartId(userCart.getId());
		cartItem.setProductId(model.getProductId());
		cartItem.setQuantity(model.getQuantity());
		cartItem.setUnitPrice(inventory.getProduct().getUnitPrice());

		return cartItemDatabaseAdapter.save(cartItem);
	}

}
