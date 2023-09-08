package com.wolfhack.payment.service;

import com.google.common.base.Strings;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.PaymentMethodCreateParams;
import com.stripe.param.PaymentSourceCollectionCreateParams;
import com.wolfhack.payment.adapter.database.CustomerDatabaseAdapter;
import com.wolfhack.payment.adapter.database.PaymentMethodDatabaseAdapter;
import com.wolfhack.payment.client.UserClient;
import com.wolfhack.payment.model.domain.CustomerInformation;
import com.wolfhack.payment.model.domain.PaymentMethod;
import com.wolfhack.payment.model.dto.CustomerCreateDTO;
import com.wolfhack.payment.model.dto.PaymentMethodCreateDTO;
import com.wolfhack.payment.model.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final UserClient userClient;
	private final CustomerDatabaseAdapter customerDatabaseAdapter;
	private final PaymentMethodDatabaseAdapter paymentMethodDatabaseAdapter;

	public Customer create(CustomerCreateDTO customerCreateDTO) {
		CustomerInformation customerInformation = customerDatabaseAdapter.getByUserId(customerCreateDTO.getUserId());
		if (Strings.isNullOrEmpty(customerInformation.getReference())) {
			return createCustomer(customerCreateDTO);
		} else {
			try {
				return Customer.retrieve(customerInformation.getReference());
			} catch (StripeException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private Customer createCustomer(CustomerCreateDTO customerCreateDTO) {
		UserDTO user = userClient.getUser(customerCreateDTO.getUserId());

		CustomerCreateParams customerCreateParams = CustomerCreateParams.builder()
				.setEmail(user.getEmail())
				.setPhone(user.getPhoneNumber())
				.build();

		try {
			Customer customer = Customer.create(customerCreateParams);

			CustomerInformation customerInformation = new CustomerInformation();
			customerInformation.setUserId(customerCreateDTO.getUserId());
			customerInformation.setPaymentMethodId(createPaymentMethod(user.getId(), customerCreateDTO, customer));
			customerInformation.setReference(customer.getId());

			customerDatabaseAdapter.save(customerInformation);

			return customer;
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
	}

	private Long createPaymentMethod(Long userId, CustomerCreateDTO customerCreateDTO, Customer customer) throws StripeException {
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setPaymentMethodType(paymentMethod.getPaymentMethodType());
		paymentMethod.setCreatedDate(LocalDate.now());
		paymentMethod.setIsDefault(true);
		paymentMethod.setUserId(userId);

		PaymentSourceCollectionCreateParams paymentSourceCollectionCreateParams = PaymentSourceCollectionCreateParams.builder()
				.setSource(paymentMethod.getPaymentMethodType().getSource())
				.build();
		String paymentMethodReference = customer.getSources().create(paymentSourceCollectionCreateParams).getId();

		paymentMethod.setReference(paymentMethodReference);

		try {
			PaymentMethodCreateDTO paymentMethodCreateDTO = customerCreateDTO.getPaymentMethod();
			PaymentMethodCreateParams paymentMethodCreateParams = PaymentMethodCreateParams.builder()
					.setType(PaymentMethodCreateParams.Type.valueOf(paymentMethodCreateDTO.getPaymentMethodType()))
					.setCard(PaymentMethodCreateParams.CardDetails.builder()
							.setExpYear((long) paymentMethodCreateDTO.getExpirationDate().getYear())
							.setExpMonth((long) paymentMethodCreateDTO.getExpirationDate().getMonth().getValue())
							.setCvc(customerCreateDTO.getCardLast4Digits())
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

		return paymentMethodDatabaseAdapter.save(paymentMethod);
	}

}
