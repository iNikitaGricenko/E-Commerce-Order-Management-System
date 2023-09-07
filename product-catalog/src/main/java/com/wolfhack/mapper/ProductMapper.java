package com.wolfhack.mapper;

import com.wolfhack.model.domain.Product;
import com.wolfhack.model.dto.ProductCreationDTO;
import com.wolfhack.model.dto.ProductFullResponseDTO;
import com.wolfhack.model.dto.ProductResponseDTO;
import com.wolfhack.model.entity.ProductEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
	ProductEntity toEntity(Product product);

	Product toModel(ProductEntity productEntity);

	Product toModel(ProductCreationDTO productCreationDTO);

	ProductResponseDTO toResponse(Product product);

	ProductFullResponseDTO toFullResponse(Product product);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ProductEntity partialUpdate(Product from, @MappingTarget ProductEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	ProductEntity update(Product from, @MappingTarget ProductEntity to);
}