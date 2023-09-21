package com.wolfhack.payment.service;

import com.wolfhack.payment.adapter.database.CustomerDatabaseAdapter;
import com.wolfhack.payment.model.dto.PaymentCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final CustomerDatabaseAdapter customerDatabaseAdapter;

	public long create(PaymentCreateDTO paymentCreateDTO) {

		return 0;
	}

}
