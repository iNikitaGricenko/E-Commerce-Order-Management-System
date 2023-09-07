package com.wolfhack.persistence;

import com.wolfhack.adapter.database.PasswordResetRequestDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.PasswordResetRequestMapper;
import com.wolfhack.model.domain.PasswordResetRequest;
import com.wolfhack.model.entity.PasswordResetRequestEntity;
import com.wolfhack.repository.PasswordResetRequestRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PasswordResetRequestDatabaseGateway implements PasswordResetRequestDatabaseAdapter {

	private final PasswordResetRequestRepository passwordResetRequestRepository;
	private final PasswordResetRequestMapper passwordResetRequestMapper;

	@Override
	public Long save(PasswordResetRequest model) {
		PasswordResetRequestEntity entity = passwordResetRequestMapper.toEntity(model);

		return passwordResetRequestRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, PasswordResetRequest model) {
		PasswordResetRequestEntity updated = passwordResetRequestRepository.findById(id)
				.map(entity -> passwordResetRequestMapper.partialUpdate(model, entity))
				.orElseThrow(NotFoundException::new);

		return passwordResetRequestRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, PasswordResetRequest model) {
		if (!exists(id)) {
			throw new NotFoundException("Password reset request does not exist");
		}
		PasswordResetRequestEntity entity = passwordResetRequestMapper.toEntity(model);
		passwordResetRequestMapper.update(model, entity);
		return passwordResetRequestRepository.save(entity).getId();
	}

	@Override
	public PasswordResetRequest getById(Long id) {
		return passwordResetRequestRepository.findById(id)
				.map(passwordResetRequestMapper::toModel)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return passwordResetRequestRepository.existsById(id);
	}

	@Override
	public Collection<PasswordResetRequest> getById(Collection<Long> ids) {
		return passwordResetRequestRepository.findAllById(ids).stream()
				.map(passwordResetRequestMapper::toModel)
				.toList();
	}

	@Override
	public List<PasswordResetRequest> getAll() {
		return passwordResetRequestRepository.findAll().stream()
				.map(passwordResetRequestMapper::toModel).toList();
	}

	@Override
	public DomainPage<PasswordResetRequest> getPage(Pageable pageable) {
		Page<PasswordResetRequest> page = passwordResetRequestRepository.findAll(pageable)
				.map(passwordResetRequestMapper::toModel);

		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		passwordResetRequestRepository.deleteById(id);
	}

	@Override
	public PasswordResetRequest getByToken(String token) {
		return passwordResetRequestRepository.findByResetToken(token)
				.map(passwordResetRequestMapper::toModel)
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public PasswordResetRequest getByUserId(Long id) {
		return passwordResetRequestRepository.findByUserId(id)
				.map(passwordResetRequestMapper::toModel)
				.orElseThrow(NotFoundException::new);
	}
}
