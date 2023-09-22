package com.wolfhack.payment.model.domain;

import com.wolfhack.payment.model.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorLog implements Serializable, DomainModel {
	private Long id;
	private String transactionId;
	private String errorMessage;
	private int errorCode;
	private LocalDate errorDate;
	private ErrorType errorType;
}