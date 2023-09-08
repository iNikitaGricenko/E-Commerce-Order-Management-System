package com.wolfhack.persistence;

import com.wolfhack.adapter.database.RelatedProductDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.RelatedProductMapper;
import com.wolfhack.model.domain.RelatedProduct;
import com.wolfhack.model.entity.RelatedProductEntity;
import com.wolfhack.repository.RelatedProductRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class RelatedProductDatabaseGateway implements RelatedProductDatabaseAdapter {

	private final RelatedProductRepository relatedProductRepository;
	private final RelatedProductMapper relatedProductMapper;

	@Override
	public Long save(RelatedProduct model) {
		RelatedProductEntity entity = relatedProductMapper.toEntity(model);
		return relatedProductRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, RelatedProduct model) {
		RelatedProductEntity updated = relatedProductRepository.findById(id)
				.map(categoryEntity -> relatedProductMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return relatedProductRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, RelatedProduct model) {
		if (!exists(id)) {
			throw new NotFoundException("Product does not exist");
		}
		RelatedProductEntity entity = relatedProductMapper.toEntity(model);
		relatedProductMapper.update(model, entity);
		return relatedProductRepository.save(entity).getId();
	}

	@Override
	public RelatedProduct getById(Long id) {
		return relatedProductRepository.findById(id).map(relatedProductMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return relatedProductRepository.existsById(id);
	}

	@Override
	public Collection<RelatedProduct> getById(Collection<Long> ids) {
		return relatedProductRepository.findAllById(ids).stream().map(relatedProductMapper::toModel).toList();
	}

	@Override
	public List<RelatedProduct> getAll() {
		return relatedProductRepository.findAll().stream().map(relatedProductMapper::toModel).toList();
	}

	@Override
	public DomainPage<RelatedProduct> getPage(Pageable pageable) {
		Page<RelatedProduct> page = relatedProductRepository.findAll(pageable).map(relatedProductMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		relatedProductRepository.deleteById(id);
	}
}
