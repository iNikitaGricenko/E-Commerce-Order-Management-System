package com.wolfhack.persistence;

import com.wolfhack.adapter.database.UserDatabaseAdapter;
import com.wolfhack.mapper.UserMapper;
import com.wolfhack.model.domain.User;
import com.wolfhack.repository.UserRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
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
		return null;
	}

	@Override
	public Long partialUpdate(Long id, User model) {
		return null;
	}

	@Override
	public Long update(Long id, User model) {
		return null;
	}

	@Override
	public User getById(Long id) {
		return null;
	}

	@Override
	public boolean exists(Long id) {
		return false;
	}

	@Override
	public Collection<User> getById(Collection<Long> ids) {
		return null;
	}

	@Override
	public List<User> getAll() {
		return null;
	}

	@Override
	public DomainPage<User> getPage(Pageable pageable) {
		return null;
	}

	@Override
	public void delete(Long id) {

	}

	@Override
	public User getByUsername(String username) {
		return null;
	}
}
