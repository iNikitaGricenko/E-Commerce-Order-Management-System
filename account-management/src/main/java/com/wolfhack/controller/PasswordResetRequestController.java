package com.wolfhack.controller;

import com.wolfhack.model.dto.PasswordResetDTO;
import com.wolfhack.service.PasswordManagement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password/reset")
@RequiredArgsConstructor
public class PasswordResetRequestController {

	private final PasswordManagement passwordManagement;

	@PostMapping
	public void sendResetRequest(@RequestBody @Valid PasswordResetDTO passwordResetDTO) {
		passwordManagement.resetPasswordRequest(passwordResetDTO.getEmail());
	}


	@PostMapping("/{resetToken}")
	public void sendResetRequest(@PathVariable String resetToken, @RequestParam String password) {
		passwordManagement.changePassword(resetToken, password);
	}

}
