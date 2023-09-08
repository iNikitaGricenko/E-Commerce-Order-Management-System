package com.wolfhack.persistence;

import com.wolfhack.adapter.database.ProductVariantDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.ProductVariantMapper;
import com.wolfhack.model.domain.ProductVariant;
import com.wolfhack.model.entity.ProductVariantEntity;
import com.wolfhack.repository.ProductVariantRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ProductVariantDatabaseGateway implements ProductVariantDatabaseAdapter {

	private final ProductVariantRepository productVariantRepository;
	private final ProductVariantMapper productVariantMapper;

	@Override
	public Long save(ProductVariant model) {
		ProductVariantEntity entity = productVariantMapper.toEntity(model);
		return productVariantRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, ProductVariant model) {
		ProductVariantEntity updated = productVariantRepository.findById(id)
				.map(categoryEntity -> productVariantMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return productVariantRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, ProductVariant model) {
		if (!exists(id)) {
			throw new NotFoundException("Product does not exist");
		}
		ProductVariantEntity entity = productVariantMapper.toEntity(model);
		productVariantMapper.update(model, entity);
		return productVariantRepository.save(entity).getId();
	}

	@Override
	public ProductVariant getById(Long id) {
		return productVariantRepository.findById(id).map(productVariantMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return productVariantRepository.existsById(id);
	}

	@Override
	public Collection<ProductVariant> getById(Collection<Long> ids) {
		return productVariantRepository.findAllById(ids).stream().map(productVariantMapper::toModel).toList();
	}

	@Override
	public List<ProductVariant> getAll() {
		return productVariantRepository.findAll().stream().map(productVariantMapper::toModel).toList();
	}

	@Override
	public DomainPage<ProductVariant> getPage(Pageable pageable) {
		Page<ProductVariant> page = productVariantRepository.findAll(pageable).map(productVariantMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		productVariantRepository.deleteById(id);
	}

	@Override
	public List<ProductVariant> getByProduct(Long productId) {
		return productVariantRepository.findAllByProductId(productId).stream()
				.map(productVariantMapper::toModel)
				.toList();
	}
}
