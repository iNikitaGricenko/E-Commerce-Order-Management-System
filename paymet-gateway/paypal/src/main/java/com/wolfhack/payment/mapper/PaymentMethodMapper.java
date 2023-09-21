package com.wolfhack.payment.mapper;

import com.wolfhack.payment.model.domain.PaymentMethod;
import com.wolfhack.payment.model.entity.PaymentMethodDetails;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentMethodMapper {
	PaymentMethodDetails toEntity(PaymentMethod paymentMethod);

	PaymentMethod toModel(PaymentMethodDetails paymentMethodDetails);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PaymentMethodDetails partialUpdate(PaymentMethod from, @MappingTarget PaymentMethodDetails to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	PaymentMethodDetails update(PaymentMethod from, @MappingTarget PaymentMethodDetails to);
}