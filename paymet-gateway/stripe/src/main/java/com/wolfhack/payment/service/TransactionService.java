package com.wolfhack.payment.service;

import com.stripe.model.Customer;
import com.wolfhack.payment.adapter.database.CustomerDatabaseAdapter;
import com.wolfhack.payment.model.domain.CustomerInformation;
import com.wolfhack.payment.model.dto.CustomerCreateDTO;
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
		CustomerCreateDTO user = paymentCreateDTO.getUser();
		CustomerInformation customerInformation = customerDatabaseAdapter.getById(user.getUserId());

		PaymentMethodCreateDTO paymentMethod = user.getPaymentMethod();

		Customer customer = customerService.create(user);

		return 0;
	}

}
