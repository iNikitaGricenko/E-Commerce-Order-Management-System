package com.wolfhack.payment.persistence;

import com.wolfhack.payment.adapter.database.PaymentLogDatabaseAdapter;
import com.wolfhack.payment.exception.NotFoundException;
import com.wolfhack.payment.mapper.PaymentLogMapper;
import com.wolfhack.payment.model.domain.PaymentLog;
import com.wolfhack.payment.model.entity.PaymentLogEntity;
import com.wolfhack.payment.repository.PaymentLogRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class PaymentLogDatabaseGateway implements PaymentLogDatabaseAdapter {

	private final PaymentLogRepository paymentLogRepository;
	private final PaymentLogMapper paymentLogMapper;

	@Override
	public Long save(PaymentLog model) {
		PaymentLogEntity entity = paymentLogMapper.toEntity(model);
		return paymentLogRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, PaymentLog model) {
		PaymentLogEntity updated = paymentLogRepository.findById(id)
				.map(categoryEntity -> paymentLogMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return paymentLogRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, PaymentLog model) {
		if (!exists(id)) {
			throw new NotFoundException("Payment Log does not exist");
		}
		PaymentLogEntity entity = paymentLogMapper.toEntity(model);
		paymentLogMapper.update(model, entity);
		return paymentLogRepository.save(entity).getId();
	}

	@Override
	public PaymentLog getById(Long id) {
		return paymentLogRepository.findById(id)
				.map(paymentLogMapper::toModel)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return paymentLogRepository.existsById(id);
	}

	@Override
	public Collection<PaymentLog> getById(Collection<Long> ids) {
		return paymentLogRepository.findAllById(ids).stream()
				.map(paymentLogMapper::toModel)
				.toList();
	}

	@Override
	public List<PaymentLog> getAll() {
		return paymentLogRepository.findAll().stream()
				.map(paymentLogMapper::toModel)
				.toList();
	}

	@Override
	public void delete(Long id) {
		paymentLogRepository.deleteById(id);
	}
}
