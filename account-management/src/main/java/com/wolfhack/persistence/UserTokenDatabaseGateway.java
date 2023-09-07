package com.wolfhack.persistence;

import com.wolfhack.adapter.database.UserTokenDatabaseAdapter;
import com.wolfhack.mapper.UserTokenMapper;
import com.wolfhack.model.domain.UserToken;
import com.wolfhack.model.entity.UserTokenEntity;
import com.wolfhack.repository.UserTokenRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTokenDatabaseGateway implements UserTokenDatabaseAdapter {

	private final UserTokenRepository userTokenRepository;
	private final UserTokenMapper userTokenMapper;

	@Override
	public Long save(UserToken model) {
		UserTokenEntity entity = userTokenMapper.toEntity(model);

		return userTokenRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, UserToken model) {
		UserTokenEntity updated = userTokenRepository.findById(id)
				.map(userTokenEntity -> userTokenMapper.partialUpdate(model, userTokenEntity))
				.orElseThrow();

		return userTokenRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, UserToken model) {
		if (!exists(id)) {
			throw new RuntimeException("Not found");
		}
		UserTokenEntity entity = userTokenMapper.toEntity(model);
		userTokenMapper.update(model, entity);
		return userTokenRepository.save(entity).getId();
	}

	@Override
	public UserToken getById(Long id) {
		return userTokenRepository.findById(id).map(userTokenMapper::toModel).orElseThrow();
	}

	@Override
	public boolean exists(Long id) {
		return userTokenRepository.existsById(id);
	}

	@Override
	public Collection<UserToken> getById(Collection<Long> ids) {
		return userTokenRepository.findAllById(ids).stream().map(userTokenMapper::toModel).toList();
	}

	@Override
	public List<UserToken> getAll() {
		return userTokenRepository.findAll().stream().map(userTokenMapper::toModel).toList();
	}

	@Override
	public DomainPage<UserToken> getPage(Pageable pageable) {
		Page<UserToken> page = userTokenRepository.findAll(pageable).map(userTokenMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		userTokenRepository.deleteById(id);
	}

	@Override
	public UserToken getByToken(String token) {
		return userTokenRepository.findByToken(token).map(userTokenMapper::toModel).orElseThrow();
	}

	@Override
	public UserToken getByUserId(Long id) {
		return userTokenRepository.findByUserId(id).map(userTokenMapper::toModel).orElseThrow();
	}

	@Override
	public boolean existsByUserId(Long userId) {
		return userTokenRepository.existsByUserId(userId);
	}
}
