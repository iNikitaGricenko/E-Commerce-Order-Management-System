package com.wolfhack.persistence;

import com.wolfhack.adapter.database.ProductDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.ProductMapper;
import com.wolfhack.model.domain.Product;
import com.wolfhack.model.entity.ProductEntity;
import com.wolfhack.repository.ProductRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ProductDatabaseGateway implements ProductDatabaseAdapter {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	@Override
	public Long save(Product model) {
		ProductEntity entity = productMapper.toEntity(model);
		return productRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Product model) {
		ProductEntity updated = productRepository.findById(id)
				.map(categoryEntity -> productMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return productRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Product model) {
		if (!exists(id)) {
			throw new NotFoundException("Product does not exist");
		}
		ProductEntity entity = productMapper.toEntity(model);
		productMapper.update(model, entity);
		return productRepository.save(entity).getId();
	}

	@Override
	public Product getById(Long id) {
		return productRepository.findById(id).map(productMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return productRepository.existsById(id);
	}

	@Override
	public Collection<Product> getById(Collection<Long> ids) {
		return productRepository.findAllById(ids).stream().map(productMapper::toModel).toList();
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll().stream().map(productMapper::toModel).toList();
	}

	@Override
	public DomainPage<Product> getPage(Pageable pageable) {
		Page<Product> page = productRepository.findAll(pageable).map(productMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> getAllByCategory(Long categoryId) {
		return productRepository.findAllByCategoryId(categoryId).stream().map(productMapper::toModel).toList();
	}
}
