package com.wolfhack.payment.persistence;

import com.wolfhack.payment.adapter.database.PaymentTransactionDatabaseAdapter;
import com.wolfhack.payment.exception.NotFoundException;
import com.wolfhack.payment.mapper.PaymentTransactionMapper;
import com.wolfhack.payment.model.domain.PaymentTransaction;
import com.wolfhack.payment.model.entity.PaymentTransactionLog;
import com.wolfhack.payment.repository.PaymentTransactionRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class PaymentTransactionDatabaseGateway implements PaymentTransactionDatabaseAdapter {

	private final PaymentTransactionRepository paymentTransactionRepository;
	private final PaymentTransactionMapper paymentTransactionMapper;

	@Override
	public Long save(PaymentTransaction model) {
		PaymentTransactionLog entity = paymentTransactionMapper.toEntity(model);
		return paymentTransactionRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, PaymentTransaction model) {
		PaymentTransactionLog updated = paymentTransactionRepository.findById(id)
				.map(categoryEntity -> paymentTransactionMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return paymentTransactionRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, PaymentTransaction model) {
		if (!exists(id)) {
			throw new NotFoundException("Transaction does not exist");
		}
		PaymentTransactionLog entity = paymentTransactionMapper.toEntity(model);
		paymentTransactionMapper.update(model, entity);
		return paymentTransactionRepository.save(entity).getId();
	}

	@Override
	public PaymentTransaction getById(Long id) {
		return paymentTransactionRepository.findById(id).map(paymentTransactionMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return paymentTransactionRepository.existsById(id);
	}

	@Override
	public Collection<PaymentTransaction> getById(Collection<Long> ids) {
		return paymentTransactionRepository.findAllById(ids).stream().map(paymentTransactionMapper::toModel).toList();
	}

	@Override
	public List<PaymentTransaction> getAll() {
		return paymentTransactionRepository.findAll().stream().map(paymentTransactionMapper::toModel).toList();
	}

	@Override
	public void delete(Long id) {
		paymentTransactionRepository.deleteById(id);
	}
}
