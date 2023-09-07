package com.wolfhack.mapper;

import com.wolfhack.model.domain.ProductImage;
import com.wolfhack.model.dto.ProductImageCreationDTO;
import com.wolfhack.model.dto.ProductImageResponseDTO;
import com.wolfhack.model.entity.ProductImageEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductImageMapper {
	ProductImageEntity toEntity(ProductImage productImage);

	ProductImage toModel(ProductImageEntity productImageEntity);

	ProductImage toModel(ProductImageCreationDTO productImageCreationDTO);

	ProductImageResponseDTO toResponse(ProductImage productImage);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ProductImageEntity partialUpdate(ProductImage from, @MappingTarget ProductImageEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	ProductImageEntity update(ProductImage from, @MappingTarget ProductImageEntity to);
}