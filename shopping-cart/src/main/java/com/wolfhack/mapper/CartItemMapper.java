package com.wolfhack.mapper;

import com.wolfhack.model.domain.CartItem;
import com.wolfhack.model.entity.CartItemEntity;
import com.wolfhack.model.entity.CartItemResponseDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CartItemMapper {
	CartItemEntity toEntity(CartItem cartItem);

	CartItem toModel(CartItemEntity cartItemEntity);

	CartItemResponseDTO toResponse(CartItem cartItem);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	CartItemEntity partialUpdate(CartItem from, @MappingTarget CartItemEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	CartItemEntity update(CartItem from, @MappingTarget CartItemEntity to);
}