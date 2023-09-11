package com.wolfhack.persistence;

import com.wolfhack.adapter.database.CartItemDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.logging.annotations.DatabaseOperation;
import com.wolfhack.mapper.CartItemMapper;
import com.wolfhack.model.domain.CartItem;
import com.wolfhack.model.entity.CartItemEntity;
import com.wolfhack.repository.CartItemRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemDatabaseGateway implements CartItemDatabaseAdapter {

	private final CartItemRepository cartItemRepository;
	private final CartItemMapper cartItemMapper;

	@Override
	@DatabaseOperation
	public Long save(CartItem model) {
		CartItemEntity entity = cartItemMapper.toEntity(model);
		return cartItemRepository.save(entity).getId();
	}

	@Override
	@DatabaseOperation
	public Long partialUpdate(Long id, CartItem model) {
		CartItemEntity updated = cartItemRepository.findById(id)
				.map(categoryEntity -> cartItemMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return cartItemRepository.save(updated).getId();
	}

	@Override
	@DatabaseOperation
	public Long update(Long id, CartItem model) {
		if (!exists(id)) {
			throw new NotFoundException("CartItem does not exist");
		}
		CartItemEntity entity = cartItemMapper.toEntity(model);
		cartItemMapper.update(model, entity);
		return cartItemRepository.save(entity).getId();
	}

	@Override
	@DatabaseOperation
	public CartItem getById(Long id) {
		return cartItemRepository.findById(id).map(cartItemMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	@DatabaseOperation
	public boolean exists(Long id) {
		return cartItemRepository.existsById(id);
	}

	@Override
	@DatabaseOperation
	public Collection<CartItem> getById(Collection<Long> ids) {
		return cartItemRepository.findAllById(ids).stream().map(cartItemMapper::toModel).toList();
	}

	@Override
	@DatabaseOperation
	public List<CartItem> getAll() {
		return cartItemRepository.findAll().stream().map(cartItemMapper::toModel).toList();
	}

	@Override
	@DatabaseOperation
	public DomainPage<CartItem> getPage(Pageable pageable) {
		Page<CartItem> page = cartItemRepository.findAll(pageable).map(cartItemMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	@DatabaseOperation
	public void delete(Long id) {
		cartItemRepository.deleteById(id);
	}

	@Override
	@DatabaseOperation
	public List<CartItem> getByCartId(Long cartId) {
		return cartItemRepository.findAllByCartId(cartId).stream().map(cartItemMapper::toModel).toList();
	}
}
