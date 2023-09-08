package com.wolfhack.persistence;

import com.wolfhack.adapter.database.ProductReviewDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.ProductReviewMapper;
import com.wolfhack.model.domain.ProductReview;
import com.wolfhack.model.entity.ProductReviewEntity;
import com.wolfhack.repository.ProductReviewRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ProductReviewDatabaseGateway implements ProductReviewDatabaseAdapter {

	private final ProductReviewRepository productReviewRepository;
	private final ProductReviewMapper productReviewMapper;

	@Override
	public Long save(ProductReview model) {
		ProductReviewEntity entity = productReviewMapper.toEntity(model);
		return productReviewRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, ProductReview model) {
		ProductReviewEntity updated = productReviewRepository.findById(id)
				.map(categoryEntity -> productReviewMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return productReviewRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, ProductReview model) {
		if (!exists(id)) {
			throw new NotFoundException("Product does not exist");
		}
		ProductReviewEntity entity = productReviewMapper.toEntity(model);
		productReviewMapper.update(model, entity);
		return productReviewRepository.save(entity).getId();
	}

	@Override
	public ProductReview getById(Long id) {
		return productReviewRepository.findById(id).map(productReviewMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return productReviewRepository.existsById(id);
	}

	@Override
	public Collection<ProductReview> getById(Collection<Long> ids) {
		return productReviewRepository.findAllById(ids).stream().map(productReviewMapper::toModel).toList();
	}

	@Override
	public List<ProductReview> getAll() {
		return productReviewRepository.findAll().stream().map(productReviewMapper::toModel).toList();
	}

	@Override
	public DomainPage<ProductReview> getPage(Pageable pageable) {
		Page<ProductReview> page = productReviewRepository.findAll(pageable).map(productReviewMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		productReviewRepository.deleteById(id);
	}

	@Override
	public List<ProductReview> getAllByProduct(Long productId) {
		return productReviewRepository.findAllByProductId(productId).stream()
				.map(productReviewMapper::toModel)
				.toList();
	}
}
