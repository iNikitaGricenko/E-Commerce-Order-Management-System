package com.wolfhack.payment.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfhack.payment.model.domain.DomainModel;
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
}