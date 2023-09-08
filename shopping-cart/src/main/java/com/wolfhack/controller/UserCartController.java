package com.wolfhack.controller;

import com.wolfhack.mapper.CartItemMapper;
import com.wolfhack.mapper.UserCartMapper;
import com.wolfhack.model.domain.CartItem;
import com.wolfhack.model.dto.CartItemAddDTO;
import com.wolfhack.model.entity.UserCartResponseDTO;
import com.wolfhack.service.UserCartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserCartController {

	private final UserCartService userCartService;
	private final UserCartMapper userCartMapper;
	private final CartItemMapper cartItemMapper;

	@GetMapping("/{userId}")
	public UserCartResponseDTO getUserCart(@PathVariable Long userId) {
		return userCartService.getUserCart(userId);
	}

	@PostMapping("/{userId}")
	public long addProduct(@PathVariable Long userId, @RequestBody @Valid CartItemAddDTO cartItemAddDTO) {
		CartItem model = cartItemMapper.toModel(cartItemAddDTO);
		return userCartService.addProduct(userId, model);
	}

}
