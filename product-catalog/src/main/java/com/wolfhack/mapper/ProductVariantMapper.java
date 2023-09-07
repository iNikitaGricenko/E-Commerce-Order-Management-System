package com.wolfhack.mapper;

import com.wolfhack.model.domain.ProductVariant;
import com.wolfhack.model.dto.ProductVariantCreationDTO;
import com.wolfhack.model.dto.ProductVariantResponseDTO;
import com.wolfhack.model.entity.ProductVariantEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductVariantMapper {
	ProductVariantEntity toEntity(ProductVariant productVariant);

	ProductVariant toModel(ProductVariantEntity productVariantEntity);

	@Mapping(source = "stockQuantity", target = "variantStockQuantity")
	@Mapping(source = "price", target = "variantPrice")
	@Mapping(source = "description", target = "variantDescription")
	@Mapping(source = "name", target = "variantName")
	ProductVariant toModel(ProductVariantCreationDTO productVariantCreationDTO);

	ProductVariantResponseDTO toResponse(ProductVariant productVariant);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ProductVariantEntity partialUpdate(ProductVariant from, @MappingTarget ProductVariantEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	ProductVariantEntity update(ProductVariant from, @MappingTarget ProductVariantEntity to);
}