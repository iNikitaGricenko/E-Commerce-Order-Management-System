package com.wolfhack.payment.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.time.LocalDate;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMethodCreateDTO implements Serializable {

	@NotEmpty
	@NotNull
	@NotBlank
	@JsonProperty("payment_method_type")
	String paymentMethodType;

	@NotEmpty
	@NotNull
	@NotBlank
	@JsonProperty("expiration_date")
	LocalDate expirationDate;

	@NotEmpty
	@NotNull
	@NotBlank
	@CreditCardNumber
	@JsonProperty("card_number")
	String cardNumber;

	@NotEmpty
	@NotNull
	@NotBlank
	@JsonProperty("cardholder_name")
	String cardHolderName;

	@NotEmpty
	@NotNull
	@NotBlank
	@JsonProperty("card_type")
	String cardType;

	@NotEmpty
	@NotNull
	@NotBlank
	@JsonProperty("card_last4_digits")
	String cardLast4Digits;

	@NotEmpty
	@NotNull
	@NotBlank
	@JsonProperty("billing_address")
	String billingAddress;
}