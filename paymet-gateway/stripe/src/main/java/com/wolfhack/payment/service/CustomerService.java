package com.wolfhack.payment.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.param.*;
import com.wolfhack.payment.adapter.database.CustomerDatabaseAdapter;
import com.wolfhack.payment.adapter.database.PaymentMethodDatabaseAdapter;
import com.wolfhack.payment.adapter.database.PaymentTransactionDatabaseAdapter;
import com.wolfhack.payment.client.UserClient;
import com.wolfhack.payment.model.domain.CustomerInformation;
import com.wolfhack.payment.model.domain.PaymentMethod;
import com.wolfhack.payment.model.domain.PaymentTransaction;
import com.wolfhack.payment.model.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final UserClient userClient;
	private final CustomerDatabaseAdapter customerDatabaseAdapter;
	private final PaymentMethodDatabaseAdapter paymentMethodDatabaseAdapter;
	private final PaymentTransactionDatabaseAdapter paymentTransactionDatabaseAdapter;

	public Long create(PaymentCreateDTO paymentCreateDTO) {
		CustomerInformation customerInformation = customerDatabaseAdapter.getByUserId(paymentCreateDTO.getUserId());
		Customer customer = addPaymentMethod(customerInformation, paymentCreateDTO.getPaymentMethod());

		ChargeCreateParams customerCreateParams = ChargeCreateParams.builder()
				.setCustomer(customerInformation.getReference())
				.setAmount(paymentCreateDTO.getPaymentAmount())
				.setCapture(true)
				.build();

		try {
			String chargeReference = Charge.create(customerCreateParams).getId();

			PaymentTransaction paymentTransaction = new PaymentTransaction();
			paymentTransaction.setCreatedDate(LocalDate.now());
			paymentTransaction.setPaymentAmount(paymentCreateDTO.getPaymentAmount());
			paymentTransaction.setPaymentMethod(customerInformation.getPaymentMethodId());
			paymentTransaction.setOrderId(paymentCreateDTO.getOrderId());
			paymentTransaction.setTransactionDate(LocalDate.now());
			paymentTransaction.setPaymentReference(chargeReference);

			return paymentTransactionDatabaseAdapter.save(paymentTransaction);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
	}

	public void createCustomer(UserRegisteredNotificationDTO userRegistered) {
		CustomerCreateParams customerCreateParams = CustomerCreateParams.builder()
				.setEmail(userRegistered.email())
				.setPhone(userRegistered.phoneNumber())
				.build();

		try {
			Customer customer = Customer.create(customerCreateParams);

			CustomerInformation customerInformation = new CustomerInformation();
			customerInformation.setUserId(userRegistered.id());
			customerInformation.setReference(customer.getId());

			customerDatabaseAdapter.save(customerInformation);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
	}

	private Customer addPaymentMethod(CustomerInformation customerInformation, PaymentMethodCreateDTO paymentMethodCreateDTO) {
		try {
			Customer customer = Customer.retrieve(customerInformation.getReference());
			customerInformation.setPaymentMethodId(createPaymentMethod(customerInformation, paymentMethodCreateDTO, customer));
			customerInformation.setUpdatedDate(LocalDate.now());

			customerDatabaseAdapter.save(customerInformation);

			return customer;
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
	}

	private Long createPaymentMethod(CustomerInformation customerInformation, PaymentMethodCreateDTO paymentMethodCreateDTO, Customer customer) {
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setPaymentMethodType(paymentMethod.getPaymentMethodType());
		paymentMethod.setCreatedDate(LocalDate.now());
		paymentMethod.setIsDefault(true);
		paymentMethod.setUserId(customerInformation.getUserId());

		PaymentSourceCollectionCreateParams paymentSourceCollectionCreateParams = PaymentSourceCollectionCreateParams.builder()
				.setSource(paymentMethod.getPaymentMethodType().getSource())
				.build();
		try {
			String paymentMethodReference = customer.getSources().create(paymentSourceCollectionCreateParams).getId();
			paymentMethod.setReference(paymentMethodReference);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}


		try {
			PaymentMethodCreateParams paymentMethodCreateParams = PaymentMethodCreateParams.builder()
					.setType(PaymentMethodCreateParams.Type.valueOf(paymentMethodCreateDTO.getPaymentMethodType()))
					.setCard(PaymentMethodCreateParams.CardDetails.builder()
							.setNumber(paymentMethodCreateDTO.getCardNumber())
							.setExpYear((long) paymentMethodCreateDTO.getExpirationDate().getYear())
							.setExpMonth((long) paymentMethodCreateDTO.getExpirationDate().getMonth().getValue())
							.setCvc(paymentMethodCreateDTO.getCardLast4Digits())
							.build())
					.build();

			com.stripe.model.PaymentMethod stripePaymentMethod = com.stripe.model.PaymentMethod.create(paymentMethodCreateParams);
			PaymentMethodAttachParams paymentMethodAttachParams = PaymentMethodAttachParams.builder()
					.setCustomer(customer.getId())
					.build();

			stripePaymentMethod.attach(paymentMethodAttachParams);
		} catch (StripeException e) {
			throw new RuntimeException();
		}

		Long paymentMethodId = paymentMethodDatabaseAdapter.save(paymentMethod);
		customerInformation.setPaymentMethodId(paymentMethodId);

		customerDatabaseAdapter.partialUpdate(customerInformation.getId(), customerInformation);

		return paymentMethodId;
	}

}
