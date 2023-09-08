package com.wolfhack.payment.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfhack.payment.model.domain.DomainModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

@Value
public class CustomerCreateDTO implements Serializable {

	@NotNull
	@Min(0)
	@JsonProperty("user_id")
	Long userId;

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

	@Valid
	@NotNull
	@JsonProperty("payment_method")
	PaymentMethodCreateDTO paymentMethod;
}