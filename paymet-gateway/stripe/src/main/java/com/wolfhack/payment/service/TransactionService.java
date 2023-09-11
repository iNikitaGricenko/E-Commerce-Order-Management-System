package com.wolfhack.payment.service;

import com.wolfhack.payment.adapter.database.CustomerDatabaseAdapter;
import com.wolfhack.payment.model.domain.CustomerInformation;
import com.wolfhack.payment.model.dto.PaymentCreateDTO;
import com.wolfhack.payment.model.dto.PaymentMethodCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final CustomerService customerService;
	private final CustomerDatabaseAdapter customerDatabaseAdapter;

	public long create(PaymentCreateDTO paymentCreateDTO) {
		CustomerInformation customerInformation = customerDatabaseAdapter.getById(paymentCreateDTO.getUserId());

		PaymentMethodCreateDTO paymentMethod = paymentCreateDTO.getPaymentMethod();

		Long paymentTransactionId = customerService.create(paymentCreateDTO);

		return paymentTransactionId;
	}

}
