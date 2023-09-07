package com.wolfhack.controller;

import com.wolfhack.mapper.UserMapper;
import com.wolfhack.mapper.UserTokenMapper;
import com.wolfhack.model.domain.User;
import com.wolfhack.model.domain.UserToken;
import com.wolfhack.model.dto.UserLoginDTO;
import com.wolfhack.model.dto.UserProfileEditDTO;
import com.wolfhack.model.dto.UserTokenResponseDTO;
import com.wolfhack.model.entity.UserLoginResponseDTO;
import com.wolfhack.service.ProfileManagement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileManagement profileManagement;
	private final UserTokenMapper userTokenMapper;
	private final UserMapper userMapper;

	@PostMapping("/{id}")
	public void editProfile(@PathVariable Long id, @RequestBody UserProfileEditDTO userProfileEditDTO) {
		User model = userMapper.toModel(userProfileEditDTO);
		profileManagement.update(id, model);
	}

	@PostMapping("/login")
	public void login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
		profileManagement.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
	}

	@PostMapping("/login/{token}")
	public UserTokenResponseDTO login(@PathVariable String token) {
		UserToken login = profileManagement.login(token);
		return userTokenMapper.toResponse(login);
	}

	@PostMapping("/{userId}/{token}")
	public void setToken(@PathVariable Long userId, @PathVariable String token) {
		profileManagement.setToken(userId, token);
	}

	@GetMapping("/{username}")
	public UserLoginResponseDTO getUser(@PathVariable String username) {
		User user = profileManagement.getByUsername(username);
		return userMapper.toLoginResponse(user);
	}

}
