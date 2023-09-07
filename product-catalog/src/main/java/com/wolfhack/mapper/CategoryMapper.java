package com.wolfhack.mapper;

import com.wolfhack.model.domain.Category;
import com.wolfhack.model.dto.CategoryCreationDTO;
import com.wolfhack.model.entity.CategoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
	CategoryEntity toEntity(Category category);

	Category toModel(CategoryEntity categoryEntity);

	Category toModel(CategoryCreationDTO categoryCreationDTO);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	CategoryEntity partialUpdate(Category from, @MappingTarget CategoryEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	CategoryEntity update(Category from, @MappingTarget CategoryEntity to);
}