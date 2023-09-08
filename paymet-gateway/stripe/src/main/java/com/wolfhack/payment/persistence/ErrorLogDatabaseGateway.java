package com.wolfhack.payment.persistence;

import com.wolfhack.payment.adapter.database.ErrorLogDatabaseAdapter;
import com.wolfhack.payment.exception.NotFoundException;
import com.wolfhack.payment.mapper.ErrorLogMapper;
import com.wolfhack.payment.model.domain.ErrorLog;
import com.wolfhack.payment.model.entity.ErrorLogEntity;
import com.wolfhack.payment.repository.ErrorLogRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ErrorLogDatabaseGateway implements ErrorLogDatabaseAdapter {

	private final ErrorLogRepository errorLogRepository;
	private final ErrorLogMapper errorLogMapper;

	@Override
	public Long save(ErrorLog model) {
		ErrorLogEntity entity = errorLogMapper.toEntity(model);
		return errorLogRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, ErrorLog model) {
		ErrorLogEntity updated = errorLogRepository.findById(id)
				.map(categoryEntity -> errorLogMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return errorLogRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, ErrorLog model) {
		if (!exists(id)) {
			throw new NotFoundException("Error Log does not exist");
		}
		ErrorLogEntity entity = errorLogMapper.toEntity(model);
		errorLogMapper.update(model, entity);
		return errorLogRepository.save(entity).getId();
	}

	@Override
	public ErrorLog getById(Long id) {
		return errorLogRepository.findById(id).map(errorLogMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return errorLogRepository.existsById(id);
	}

	@Override
	public Collection<ErrorLog> getById(Collection<Long> ids) {
		return errorLogRepository.findAllById(ids).stream().map(errorLogMapper::toModel).toList();
	}

	@Override
	public List<ErrorLog> getAll() {
		return errorLogRepository.findAll().stream().map(errorLogMapper::toModel).toList();
	}

	@Override
	public void delete(Long id) {
		errorLogRepository.deleteById(id);
	}
}
