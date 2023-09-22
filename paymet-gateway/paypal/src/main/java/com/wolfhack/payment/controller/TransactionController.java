package com.wolfhack.payment.controller;

import com.wolfhack.payment.model.dto.PaymentCreateDTO;
import com.wolfhack.payment.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping
	public ResponseEntity<Long> create(@RequestBody @Valid PaymentCreateDTO paymentCreateDTO) {
		TransactionService.TransactionResponse response = transactionService.create(paymentCreateDTO);

		return ResponseEntity.status(302)
			.header("Location", response.getRedirectUrl())
			.body(response.getId());
	}

}
