package com.wolfhack.mapper;

import com.wolfhack.model.domain.OrderItems;
import com.wolfhack.model.entity.OrderItemsEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemsMapper {
	OrderItemsEntity toEntity(OrderItems orderItems);

	OrderItems toModel(OrderItemsEntity orderItemsEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	OrderItemsEntity partialUpdate(OrderItems from, @MappingTarget OrderItemsEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	OrderItemsEntity update(OrderItems from, @MappingTarget OrderItemsEntity to);
}