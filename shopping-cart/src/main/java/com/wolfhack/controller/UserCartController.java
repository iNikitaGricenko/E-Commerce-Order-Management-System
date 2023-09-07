package com.wolfhack.controller;

import com.wolfhack.mapper.UserCartMapper;
import com.wolfhack.model.entity.UserCartResponseDTO;
import com.wolfhack.service.UserCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserCartController {

	private final UserCartService userCartService;
	private final UserCartMapper userCartMapper;

	@GetMapping("/{userID}")
	public UserCartResponseDTO getUserCart(@PathVariable Long userId) {
		return userCartService.getUserCart(userId);
	}

}
