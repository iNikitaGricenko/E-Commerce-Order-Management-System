package com.wolfhack.model.dto;

import com.wolfhack.model.domain.PaymentTransaction;
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
	private Long userId;
	private Long orderId;
	private long paymentAmount;
	private String paymentDetails;
}