package com.wolfhack.persistence;

import com.wolfhack.adapter.database.CategoryDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.CategoryMapper;
import com.wolfhack.model.domain.Category;
import com.wolfhack.model.entity.CategoryEntity;
import com.wolfhack.repository.CategoryRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CategoryDatabaseGateway implements CategoryDatabaseAdapter {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	@Override
	public Long save(Category model) {
		CategoryEntity entity = categoryMapper.toEntity(model);
		return categoryRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, Category model) {
		CategoryEntity updated = categoryRepository.findById(id)
				.map(categoryEntity -> categoryMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return categoryRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, Category model) {
		if (!exists(id)) {
			throw new NotFoundException("Category does not exist");
		}
		CategoryEntity entity = categoryMapper.toEntity(model);
		categoryMapper.update(model, entity);
		return categoryRepository.save(entity).getId();
	}

	@Override
	public Category getById(Long id) {
		return categoryRepository.findById(id).map(categoryMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return categoryRepository.existsById(id);
	}

	@Override
	public Collection<Category> getById(Collection<Long> ids) {
		return categoryRepository.findAllById(ids).stream().map(categoryMapper::toModel).toList();
	}

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll().stream().map(categoryMapper::toModel).toList();
	}

	@Override
	public DomainPage<Category> getPage(Pageable pageable) {
		Page<Category> page = categoryRepository.findAll(pageable).map(categoryMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}
}
