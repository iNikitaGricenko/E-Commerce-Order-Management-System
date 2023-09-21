package com.wolfhack.payment.mapper;

import com.wolfhack.payment.model.domain.PaymentLog;
import com.wolfhack.payment.model.entity.PaymentLogEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentLogMapper {
	PaymentLogEntity toEntity(PaymentLog paymentLog);

	PaymentLog toModel(PaymentLogEntity paymentLogEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PaymentLogEntity partialUpdate(PaymentLog from, @MappingTarget PaymentLogEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	PaymentLogEntity update(PaymentLog from, @MappingTarget PaymentLogEntity to);
}