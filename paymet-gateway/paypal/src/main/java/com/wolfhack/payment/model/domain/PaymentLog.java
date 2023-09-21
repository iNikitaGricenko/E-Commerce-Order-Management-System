package com.wolfhack.payment.model.domain;

import com.wolfhack.payment.model.LogType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentLog implements Serializable, DomainModel {
	private Long id;
	private Long transactionId;
	private String requestPayload;
	private String responsePayload;
	private LocalDate logDate;
	private LogType logType;
}