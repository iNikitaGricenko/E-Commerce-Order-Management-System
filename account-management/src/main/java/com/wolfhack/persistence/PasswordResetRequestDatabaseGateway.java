package com.wolfhack.persistence;

import com.wolfhack.adapter.database.PasswordResetRequestDatabaseAdapter;
import com.wolfhack.mapper.PasswordResetRequestMapper;
import com.wolfhack.model.domain.PasswordResetRequest;
import com.wolfhack.repository.PasswordResetRequestRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
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
		return null;
	}

	@Override
	public Long partialUpdate(Long id, PasswordResetRequest model) {
		return null;
	}

	@Override
	public Long update(Long id, PasswordResetRequest model) {
		return null;
	}

	@Override
	public PasswordResetRequest getById(Long id) {
		return null;
	}

	@Override
	public boolean exists(Long id) {
		return false;
	}

	@Override
	public Collection<PasswordResetRequest> getById(Collection<Long> ids) {
		return null;
	}

	@Override
	public List<PasswordResetRequest> getAll() {
		return null;
	}

	@Override
	public DomainPage<PasswordResetRequest> getPage(Pageable pageable) {
		return null;
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public PasswordResetRequest getByToken(String token) {
		return null;
	}
}
