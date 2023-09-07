package com.wolfhack.persistence;

import com.wolfhack.adapter.database.InventoryDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.ProductInventoryMapper;
import com.wolfhack.model.domain.ProductInventory;
import com.wolfhack.model.entity.ProductInventoryEntity;
import com.wolfhack.repository.ProductInventoryRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryDatabaseGateway implements InventoryDatabaseAdapter {

	private final ProductInventoryRepository productInventoryRepository;
	private final ProductInventoryMapper productInventoryMapper;

	@Override
	public Long save(ProductInventory model) {
		ProductInventoryEntity entity = productInventoryMapper.toEntity(model);
		return productInventoryRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, ProductInventory model) {
		ProductInventoryEntity updated = productInventoryRepository.findById(id)
				.map(categoryEntity -> productInventoryMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return productInventoryRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, ProductInventory model) {
		if (!exists(id)) {
			throw new NotFoundException("Product inventory does not exist");
		}
		ProductInventoryEntity entity = productInventoryMapper.toEntity(model);
		productInventoryMapper.update(model, entity);
		return productInventoryRepository.save(entity).getId();
	}

	@Override
	public ProductInventory getById(Long id) {
		return productInventoryRepository.findById(id)
				.map(productInventoryMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return productInventoryRepository.existsById(id);
	}

	@Override
	public Collection<ProductInventory> getById(Collection<Long> ids) {
		return productInventoryRepository.findAllById(ids).stream()
				.map(productInventoryMapper::toModel).toList();
	}

	@Override
	public List<ProductInventory> getAll() {
		return productInventoryRepository.findAll().stream()
				.map(productInventoryMapper::toModel).toList();
	}

	@Override
	public DomainPage<ProductInventory> getPage(Pageable pageable) {
		Page<ProductInventory> page = productInventoryRepository.findAll(pageable)
				.map(productInventoryMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		productInventoryRepository.deleteById(id);
	}

	@Override
	public ProductInventory getByProductId(Long productId) {
		return productInventoryRepository.findByProductId(productId)
				.map(productInventoryMapper::toModel)
				.orElseThrow(NotFoundException::new);
	}
}
