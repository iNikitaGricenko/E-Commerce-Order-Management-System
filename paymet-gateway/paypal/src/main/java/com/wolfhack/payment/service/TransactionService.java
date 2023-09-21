package com.wolfhack.payment.service;

import com.paypal.orders.*;
import com.wolfhack.payment.adapter.database.CustomerDatabaseAdapter;
import com.wolfhack.payment.model.dto.PaymentCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final CustomerDatabaseAdapter customerDatabaseAdapter;

	public long create(PaymentCreateDTO paymentCreateDTO) {
		OrderRequest order = new OrderRequest();

		order.checkoutPaymentIntent("CAPTURE");

		AmountWithBreakdown amountWithBreakdown = new AmountWithBreakdown()
			.currencyCode(paymentCreateDTO.getCurrencyCode())
			.value(String.valueOf(paymentCreateDTO.getPaymentAmount()));

		PurchaseUnitRequest purchaseUnit = new PurchaseUnitRequest()
			.amountWithBreakdown(amountWithBreakdown);

		ApplicationContext applicationContext = new ApplicationContext()
			.returnUrl("/capture")
			.cancelUrl("/cancel");

		order.applicationContext(applicationContext);

		OrdersCreateRequest ordersCreate = new OrdersCreateRequest()
			.requestBody(order);

		order.purchaseUnits(List.of(purchaseUnit));

		return 0;
	}

}
