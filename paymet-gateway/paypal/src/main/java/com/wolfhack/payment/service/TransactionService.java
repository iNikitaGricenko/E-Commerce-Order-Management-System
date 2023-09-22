package com.wolfhack.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.paypal.core.PayPalHttpClient;
import com.paypal.orders.*;
import com.wolfhack.payment.adapter.database.ErrorLogDatabaseAdapter;
import com.wolfhack.payment.adapter.database.PaymentLogDatabaseAdapter;
import com.wolfhack.payment.adapter.database.PaymentTransactionDatabaseAdapter;
import com.wolfhack.payment.exception.ForbiddenException;
import com.wolfhack.payment.model.ErrorType;
import com.wolfhack.payment.model.LogType;
import com.wolfhack.payment.model.PaymentStatus;
import com.wolfhack.payment.model.domain.ErrorLog;
import com.wolfhack.payment.model.domain.PaymentLog;
import com.wolfhack.payment.model.domain.PaymentTransaction;
import com.wolfhack.payment.model.dto.PaymentCreateDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

	public static final String APPROVE = "approve";
	public static final String CAPTURE = "CAPTURE";

	public static final String CAPTURE_REDIRECT = "/capture";
	public static final String CANCEL_REDIRECT = "/cancel";

	private final PayPalHttpClient payPal;

	private final ErrorLogDatabaseAdapter errorLogDatabaseAdapter;
	private final PaymentLogDatabaseAdapter paymentLogDatabaseAdapter;
	private final PaymentTransactionDatabaseAdapter paymentTransactionDatabaseAdapter;

	public TransactionResponse create(PaymentCreateDTO paymentCreateDTO) {
		OrderRequest order = new OrderRequest();

		order.checkoutPaymentIntent(CAPTURE);

		PurchaseUnitRequest purchaseUnit = getPurchaseUnit(paymentCreateDTO.getCurrencyCode(), paymentCreateDTO.getPaymentAmount());
		order.purchaseUnits(List.of(purchaseUnit));

		order.applicationContext(getApplicationContext());

		try {
			OrdersCreateRequest ordersCreate = new OrdersCreateRequest().requestBody(order);

			Order orderResponse = payPal.execute(ordersCreate).result();

			Predicate<LinkDescription> approvalLink = link -> APPROVE.equals(link.rel());

			String redirectUrl = orderResponse.links().stream()
				.filter(approvalLink)
				.findFirst()
				.orElseThrow(null)
				.href();

			persistLog(paymentCreateDTO, orderResponse);

			Long persisted;
			if (Strings.isNullOrEmpty(redirectUrl)) {
				persisted = persistTransaction(paymentCreateDTO, orderResponse, PaymentStatus.FAILED);
				return TransactionResponse.builder().id(persisted).redirectUrl(redirectUrl).build();
			}

			persisted = persistTransaction(paymentCreateDTO, orderResponse, PaymentStatus.SUCCESSFUL);

			return TransactionResponse.builder().id(persisted).redirectUrl(redirectUrl).build();
		} catch (IOException exception) {
			log.error(exception.getMessage(), exception);
			persistError(exception);
			throw new ForbiddenException("Failed to execute paypal payment");
		}
	}

	private ApplicationContext getApplicationContext() {
        return new ApplicationContext().returnUrl(CAPTURE_REDIRECT).cancelUrl(CANCEL_REDIRECT);
	}

	private PurchaseUnitRequest getPurchaseUnit(@Valid @NotNull String currencyCode, @NotNull @Positive long paymentAmount) {
		AmountWithBreakdown amountWithBreakdown = new AmountWithBreakdown()
			.currencyCode(currencyCode)
			.value(String.valueOf(paymentAmount));

        return new PurchaseUnitRequest()
			.amountWithBreakdown(amountWithBreakdown);
	}

	private Long persistTransaction(PaymentCreateDTO paymentCreateDTO, Order orderResponse, PaymentStatus paymentStatus) {
		PaymentTransaction paymentTransaction = new PaymentTransaction();
		paymentTransaction.setCreatedDate(LocalDate.now());
		paymentTransaction.setTransactionDate(LocalDate.parse(orderResponse.updateTime()));
		paymentTransaction.setPaymentAmount(paymentCreateDTO.getPaymentAmount());
		paymentTransaction.setPaymentReference(orderResponse.id());
		paymentTransaction.setOrderId(paymentCreateDTO.getOrderId());
		paymentTransaction.setUserId(paymentCreateDTO.getUserId());
		paymentTransaction.setPaymentStatus(paymentStatus);

		return paymentTransactionDatabaseAdapter.save(paymentTransaction);
	}

	@Async
	protected void persistLog(PaymentCreateDTO paymentCreateDTO, Order orderResponse) {
		PaymentLog paymentLog = new PaymentLog();
		paymentLog.setTransactionId(orderResponse.id());
		paymentLog.setLogType(LogType.REQUEST);
		paymentLog.setLogDate(LocalDate.now());

		ObjectMapper objectMapper = new ObjectMapper();
		if (objectMapper.canSerialize(PaymentCreateDTO.class)) {
			paymentLog.setRequestPayload(objectMapper.convertValue(paymentCreateDTO, String.class));
		}
		if (objectMapper.canSerialize(Order.class)) {
			paymentLog.setResponsePayload(objectMapper.convertValue(orderResponse, String.class));
		}
		paymentLogDatabaseAdapter.save(paymentLog);
	}

	@Async
	protected void persistError(IOException exception) {
		ErrorLog errorLog = new ErrorLog();
		errorLog.setErrorCode(503);
		errorLog.setErrorMessage(exception.getMessage());
		errorLog.setErrorDate(LocalDate.now());
		errorLog.setErrorType(ErrorType.Communication);
		errorLogDatabaseAdapter.save(errorLog);
	}

	@Getter
	@Builder
	public static class TransactionResponse {
		private Long id;

		private String redirectUrl;
	}

}
