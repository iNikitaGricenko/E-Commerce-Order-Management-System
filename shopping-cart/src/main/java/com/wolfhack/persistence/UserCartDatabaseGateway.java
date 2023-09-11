package com.wolfhack.persistence;

import com.wolfhack.adapter.database.UserCartDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.logging.annotations.DatabaseOperation;
import com.wolfhack.mapper.UserCartMapper;
import com.wolfhack.model.domain.UserCart;
import com.wolfhack.model.entity.UserCartEntity;
import com.wolfhack.repository.UserCartRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCartDatabaseGateway implements UserCartDatabaseAdapter {

	private final UserCartRepository userCartRepository;
	private final UserCartMapper userCartMapper;

	@Override
	@DatabaseOperation
	public Long save(UserCart model) {
		UserCartEntity entity = userCartMapper.toEntity(model);
		return userCartRepository.save(entity).getId();
	}

	@Override
	@DatabaseOperation
	public Long partialUpdate(Long id, UserCart model) {
		UserCartEntity updated = userCartRepository.findById(id)
				.map(categoryEntity -> userCartMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return userCartRepository.save(updated).getId();
	}

	@Override
	@DatabaseOperation
	public Long update(Long id, UserCart model) {
		if (!exists(id)) {
			throw new NotFoundException("UserCart does not exist");
		}
		UserCartEntity entity = userCartMapper.toEntity(model);
		userCartMapper.update(model, entity);
		return userCartRepository.save(entity).getId();
	}

	@Override
	@DatabaseOperation
	public UserCart getById(Long id) {
		return userCartRepository.findById(id).map(userCartMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	@DatabaseOperation
	public boolean exists(Long id) {
		return userCartRepository.existsById(id);
	}

	@Override
	@DatabaseOperation
	public Collection<UserCart> getById(Collection<Long> ids) {
		return userCartRepository.findAllById(ids).stream().map(userCartMapper::toModel).toList();
	}

	@Override
	@DatabaseOperation
	public List<UserCart> getAll() {
		return userCartRepository.findAll().stream().map(userCartMapper::toModel).toList();
	}

	@Override
	@DatabaseOperation
	public DomainPage<UserCart> getPage(Pageable pageable) {
		Page<UserCart> page = userCartRepository.findAll(pageable).map(userCartMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	@DatabaseOperation
	public void delete(Long id) {
		userCartRepository.deleteById(id);
	}

	@Override
	@DatabaseOperation
	public UserCart getByUser(Long userId) {
		return userCartRepository.findByUserId(userId).map(userCartMapper::toModel).orElseThrow(NotFoundException::new);
	}
}
