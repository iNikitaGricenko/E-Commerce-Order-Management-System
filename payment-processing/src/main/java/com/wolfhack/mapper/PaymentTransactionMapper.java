package com.wolfhack.mapper;

import com.wolfhack.model.domain.PaymentTransaction;
import com.wolfhack.model.entity.PaymentTransactionEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentTransactionMapper {
	PaymentTransactionEntity toEntity(PaymentTransaction paymentTransaction);

	PaymentTransaction toModel(PaymentTransactionEntity paymentTransactionEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PaymentTransactionEntity partialUpdate(PaymentTransaction from, @MappingTarget PaymentTransactionEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	PaymentTransactionEntity update(PaymentTransaction from, @MappingTarget PaymentTransactionEntity to);
}