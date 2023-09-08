package com.wolfhack.payment.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolfhack.payment.model.domain.DomainModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.time.LocalDate;

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