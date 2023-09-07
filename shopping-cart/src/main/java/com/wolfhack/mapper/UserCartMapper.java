package com.wolfhack.mapper;

import com.wolfhack.model.domain.UserCart;
import com.wolfhack.model.entity.UserCartEntity;
import com.wolfhack.model.entity.UserCartResponseDTO;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserCartMapper {
	UserCartEntity toEntity(UserCart userCart);

	UserCart toModel(UserCartEntity userCartEntity);

	UserCartResponseDTO toResponse(UserCart userCart);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	UserCartEntity partialUpdate(UserCart from, @MappingTarget UserCartEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	UserCartEntity update(UserCart from, @MappingTarget UserCartEntity to);
}