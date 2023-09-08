package com.wolfhack.persistence;

import com.wolfhack.adapter.database.PaymentTransactionDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.PaymentTransactionMapper;
import com.wolfhack.model.domain.PaymentTransaction;
import com.wolfhack.model.entity.PaymentTransactionEntity;
import com.wolfhack.repository.PaymentTransactionRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class PaymentTransactionDatabaseGateway implements PaymentTransactionDatabaseAdapter {

	private final PaymentTransactionRepository paymentTransactionRepository;
	private final PaymentTransactionMapper paymentTransactionMapper;

	@Override
	public Long save(PaymentTransaction model) {
		PaymentTransactionEntity entity = paymentTransactionMapper.toEntity(model);
		return paymentTransactionRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, PaymentTransaction model) {
		PaymentTransactionEntity updated = paymentTransactionRepository.findById(id)
				.map(categoryEntity -> paymentTransactionMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return paymentTransactionRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, PaymentTransaction model) {
		if (!exists(id)) {
			throw new NotFoundException("Payment transaction does not exist");
		}
		PaymentTransactionEntity entity = paymentTransactionMapper.toEntity(model);
		paymentTransactionMapper.update(model, entity);
		return paymentTransactionRepository.save(entity).getId();
	}

	@Override
	public PaymentTransaction getById(Long id) {
		return paymentTransactionRepository.findById(id)
				.map(paymentTransactionMapper::toModel)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return paymentTransactionRepository.existsById(id);
	}

	@Override
	public Collection<PaymentTransaction> getById(Collection<Long> ids) {
		return paymentTransactionRepository.findAllById(ids).stream()
				.map(paymentTransactionMapper::toModel)
				.toList();
	}

	@Override
	public List<PaymentTransaction> getAll() {
		return paymentTransactionRepository.findAll().stream()
				.map(paymentTransactionMapper::toModel)
				.toList();
	}

	@Override
	public DomainPage<PaymentTransaction> getPage(Pageable pageable) {
		Page<PaymentTransaction> page = paymentTransactionRepository.findAll(pageable)
				.map(paymentTransactionMapper::toModel);

		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		paymentTransactionRepository.deleteById(id);
	}
}
