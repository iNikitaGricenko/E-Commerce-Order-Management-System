package com.wolfhack.mapper;

import com.wolfhack.model.domain.Order;
import com.wolfhack.model.entity.OrderEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
	OrderEntity toEntity(Order order);

	Order toModel(OrderEntity orderEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	OrderEntity partialUpdate(Order from, @MappingTarget OrderEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	OrderEntity update(Order from, @MappingTarget OrderEntity to);
}