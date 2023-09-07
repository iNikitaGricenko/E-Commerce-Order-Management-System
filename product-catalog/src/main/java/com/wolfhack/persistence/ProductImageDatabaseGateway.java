package com.wolfhack.persistence;

import com.wolfhack.adapter.database.ProductImageDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.ProductImageMapper;
import com.wolfhack.model.domain.ProductImage;
import com.wolfhack.model.entity.ProductImageEntity;
import com.wolfhack.repository.ProductImageRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductImageDatabaseGateway implements ProductImageDatabaseAdapter {

	private final ProductImageRepository productImageRepository;
	private final ProductImageMapper productImageMapper;

	@Override
	public Long save(ProductImage model) {
		ProductImageEntity entity = productImageMapper.toEntity(model);
		return productImageRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, ProductImage model) {
		ProductImageEntity updated = productImageRepository.findById(id)
				.map(categoryEntity -> productImageMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return productImageRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, ProductImage model) {
		if (!exists(id)) {
			throw new NotFoundException("Product does not exist");
		}
		ProductImageEntity entity = productImageMapper.toEntity(model);
		productImageMapper.update(model, entity);
		return productImageRepository.save(entity).getId();
	}

	@Override
	public ProductImage getById(Long id) {
		return productImageRepository.findById(id).map(productImageMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return productImageRepository.existsById(id);
	}

	@Override
	public Collection<ProductImage> getById(Collection<Long> ids) {
		return productImageRepository.findAllById(ids).stream().map(productImageMapper::toModel).toList();
	}

	@Override
	public List<ProductImage> getAll() {
		return productImageRepository.findAll().stream().map(productImageMapper::toModel).toList();
	}

	@Override
	public DomainPage<ProductImage> getPage(Pageable pageable) {
		Page<ProductImage> page = productImageRepository.findAll(pageable).map(productImageMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		productImageRepository.deleteById(id);
	}

	@Override
	public List<ProductImage> getAllByProduct(Long productId) {
		return productImageRepository.findAllByProductId(productId).stream()
				.map(productImageMapper::toModel)
				.toList();
	}
}
