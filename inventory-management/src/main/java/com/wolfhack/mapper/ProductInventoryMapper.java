package com.wolfhack.mapper;

import com.wolfhack.model.domain.ProductInventory;
import com.wolfhack.model.entity.ProductInventoryEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductInventoryMapper {
	ProductInventoryEntity toEntity(ProductInventory productInventory);

	ProductInventory toModel(ProductInventoryEntity productInventoryEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ProductInventoryEntity partialUpdate(ProductInventory from, @MappingTarget ProductInventoryEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	ProductInventoryEntity update(ProductInventory from, @MappingTarget ProductInventoryEntity to);
}