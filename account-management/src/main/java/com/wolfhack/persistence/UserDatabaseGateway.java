package com.wolfhack.persistence;

import com.wolfhack.adapter.database.UserDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.UserMapper;
import com.wolfhack.model.domain.User;
import com.wolfhack.model.entity.UserEntity;
import com.wolfhack.repository.UserRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDatabaseGateway implements UserDatabaseAdapter {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public Long save(User model) {
		UserEntity entity = userMapper.toEntity(model);
		return userRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, User model) {
		UserEntity updated = userRepository.findById(id)
				.map(userEntity -> userMapper.partialUpdate(model, userEntity))
				.orElseThrow();

		return userRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, User model) {
		if (!exists(id)) {
			throw new NotFoundException("User does not exist");
		}
		UserEntity entity = userMapper.toEntity(model);
		userMapper.update(model, entity);
		return userRepository.save(entity).getId();
	}

	@Override
	public User getById(Long id) {
		return userRepository.findById(id).map(userMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return userRepository.existsById(id);
	}

	@Override
	public Collection<User> getById(Collection<Long> ids) {
		return userRepository.findAllById(ids).stream().map(userMapper::toModel).toList();
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll().stream().map(userMapper::toModel).toList();
	}

	@Override
	public DomainPage<User> getPage(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable).map(userMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username).map(userMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.findByEmail(email).map(userMapper::toModel).orElseThrow(NotFoundException::new);
	}
}
