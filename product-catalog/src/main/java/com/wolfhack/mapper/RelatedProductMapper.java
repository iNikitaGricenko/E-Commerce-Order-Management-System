package com.wolfhack.mapper;

import com.wolfhack.model.domain.RelatedProduct;
import com.wolfhack.model.entity.RelatedProductEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RelatedProductMapper {
	RelatedProductEntity toEntity(RelatedProduct relatedProduct);

	RelatedProduct toModel(RelatedProductEntity relatedProductEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	RelatedProductEntity partialUpdate(RelatedProduct from, @MappingTarget RelatedProductEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	RelatedProductEntity update(RelatedProduct from, @MappingTarget RelatedProductEntity to);
}