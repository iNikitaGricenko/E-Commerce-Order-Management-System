package com.wolfhack.payment.model;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Getter
@RequiredArgsConstructor
public enum PaymentMethodType {

	MASTERCARD("tok_mastercard", "MasterCard", Pattern.compile("^5[1-5][0-9]{14}$")),
	VISA("tok_visa", "VISA", Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$"));

	private final String source;
	private final String cardName;
	private final Pattern pattern;

	public boolean validate(String cardNumber, int expirationYear, int expirationMonth) {
		boolean numberMatches = this.pattern.matcher(cardNumber).matches();

		LocalDate expiresAt = LocalDate.of(expirationYear, expirationMonth, 1);
		boolean notExpired = expiresAt.isAfter(LocalDate.now());

		return numberMatches && notExpired;
	}

}
