package com.wolfhack.service;

import com.wolfhack.adapter.database.PaymentTransactionDatabaseAdapter;
import com.wolfhack.model.domain.PaymentTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentTransactionDatabaseAdapter paymentTransactionDatabaseAdapter;

	public long process(PaymentTransaction paymentTransaction) {
		paymentTransaction.create();


		return 0;
	}

}
