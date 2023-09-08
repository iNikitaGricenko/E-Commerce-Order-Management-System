package com.wolfhack.persistence;

import com.wolfhack.adapter.database.OrderItemsDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.OrderItemsMapper;
import com.wolfhack.model.domain.OrderItems;
import com.wolfhack.model.entity.OrderItemsEntity;
import com.wolfhack.repository.OrderItemsRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class OrderItemsDatabaseGateway implements OrderItemsDatabaseAdapter {

	private final OrderItemsRepository orderItemsRepository;
	private final OrderItemsMapper orderItemsMapper;

	@Override
	public Long save(OrderItems model) {
		OrderItemsEntity entity = orderItemsMapper.toEntity(model);
		return orderItemsRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, OrderItems model) {
		OrderItemsEntity updated = orderItemsRepository.findById(id)
				.map(categoryEntity -> orderItemsMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return orderItemsRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, OrderItems model) {
		if (!exists(id)) {
			throw new NotFoundException("Order item does not exist");
		}
		OrderItemsEntity entity = orderItemsMapper.toEntity(model);
		orderItemsMapper.update(model, entity);
		return orderItemsRepository.save(entity).getId();
	}

	@Override
	public OrderItems getById(Long id) {
		return orderItemsRepository.findById(id).map(orderItemsMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return orderItemsRepository.existsById(id);
	}

	@Override
	public Collection<OrderItems> getById(Collection<Long> ids) {
		return orderItemsRepository.findAllById(ids).stream().map(orderItemsMapper::toModel).toList();
	}

	@Override
	public List<OrderItems> getAll() {
		return orderItemsRepository.findAll().stream().map(orderItemsMapper::toModel).toList();
	}

	@Override
	public DomainPage<OrderItems> getPage(Pageable pageable) {
		Page<OrderItems> page = orderItemsRepository.findAll(pageable).map(orderItemsMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		orderItemsRepository.deleteById(id);
	}
}
