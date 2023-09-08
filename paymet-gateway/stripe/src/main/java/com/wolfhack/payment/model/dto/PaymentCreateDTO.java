package com.wolfhack.payment.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfhack.payment.model.domain.DomainModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentCreateDTO implements Serializable {


	@JsonProperty("user_id")
	Long userId;

	@Valid
	@NotNull
	@JsonProperty("payment_method")
	PaymentMethodCreateDTO paymentMethod;

	@Min(0)
	@NotNull
	@JsonProperty("order_id")
	Long orderId;

	@NotNull
	@Positive
	@JsonProperty("payment_amount")
	long paymentAmount;
}