package com.wolfhack.payment.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

@Value
public class CustomerCreateDTO implements Serializable {

	@NotNull
	@Min(0)
	@JsonProperty("user_id")
	Long userId;

	@Valid
	@NotNull
	@JsonProperty("payment_method")
	PaymentMethodCreateDTO paymentMethod;
}