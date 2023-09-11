package com.wolfhack.service;

import com.wolfhack.adapter.database.PaymentTransactionDatabaseAdapter;
import com.wolfhack.adapter.feign.PaymentClient;
import com.wolfhack.client.MastercardClient;
import com.wolfhack.client.PayPalClient;
import com.wolfhack.client.StripeClient;
import com.wolfhack.model.PaymentMethod;
import com.wolfhack.model.domain.PaymentTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentTransactionDatabaseAdapter paymentTransactionDatabaseAdapter;
	private final StripeClient stripeClient;
	private final PayPalClient payPalClient;
	private final MastercardClient mastercardClient;

	public long process(PaymentTransaction paymentTransaction) {
		paymentTransaction.create();

		PaymentClient client = getClient(paymentTransaction.getPaymentMethod());
		client.pay(paymentTransaction.toDto());

		paymentTransaction.setPaymentDate(LocalDate.now());
		return paymentTransactionDatabaseAdapter.save(paymentTransaction);
	}

	private PaymentClient getClient(PaymentMethod paymentMethod) {
		return switch (paymentMethod) {
			case Stripe -> stripeClient;
			case PayPal -> payPalClient;
			case Mastercard -> mastercardClient;
			default -> throw new RuntimeException("Payment method not supported yet");
		};
	}

}
