package com.wolfhack.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfhack.model.domain.PaymentTransaction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link PaymentTransaction}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreationDTO implements Serializable {

	@JsonProperty("user_id")
	private Long userId;


	@Min(0)
	@NotNull
	@JsonProperty("order_id")
	private Long orderId;

	@NotNull
	@Positive
	@JsonProperty("payment_amount")
	private long paymentAmount;

	private String paymentDetails;

	@Valid
	@NotNull
	@JsonProperty("payment_method")
	private PaymentMethodCreateDTO paymentMethodCreateDTO;
}