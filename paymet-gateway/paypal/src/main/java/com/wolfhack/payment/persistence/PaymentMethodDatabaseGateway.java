package com.wolfhack.payment.persistence;

import com.wolfhack.payment.adapter.database.PaymentMethodDatabaseAdapter;
import com.wolfhack.payment.exception.NotFoundException;
import com.wolfhack.payment.mapper.PaymentMethodMapper;
import com.wolfhack.payment.model.domain.PaymentMethod;
import com.wolfhack.payment.model.entity.PaymentMethodDetails;
import com.wolfhack.payment.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class PaymentMethodDatabaseGateway implements PaymentMethodDatabaseAdapter {

	private final PaymentMethodRepository paymentMethodRepository;
	private final PaymentMethodMapper paymentMethodMapper;

	@Override
	public Long save(PaymentMethod model) {
		PaymentMethodDetails entity = paymentMethodMapper.toEntity(model);
		return paymentMethodRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, PaymentMethod model) {
		PaymentMethodDetails updated = paymentMethodRepository.findById(id)
				.map(categoryEntity -> paymentMethodMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return paymentMethodRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, PaymentMethod model) {
		if (!exists(id)) {
			throw new NotFoundException("Payment method does not exist");
		}
		PaymentMethodDetails entity = paymentMethodMapper.toEntity(model);
		paymentMethodMapper.update(model, entity);
		return paymentMethodRepository.save(entity).getId();
	}

	@Override
	public PaymentMethod getById(Long id) {
		return paymentMethodRepository.findById(id)
				.map(paymentMethodMapper::toModel)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return paymentMethodRepository.existsById(id);
	}

	@Override
	public Collection<PaymentMethod> getById(Collection<Long> ids) {
		return paymentMethodRepository.findAllById(ids).stream()
				.map(paymentMethodMapper::toModel)
				.toList();
	}

	@Override
	public List<PaymentMethod> getAll() {
		return paymentMethodRepository.findAll().stream()
				.map(paymentMethodMapper::toModel)
				.toList();
	}

	@Override
	public void delete(Long id) {
		paymentMethodRepository.deleteById(id);
	}
}
