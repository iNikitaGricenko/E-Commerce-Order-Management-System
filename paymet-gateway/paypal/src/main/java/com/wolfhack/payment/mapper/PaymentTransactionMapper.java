package com.wolfhack.payment.mapper;

import com.wolfhack.payment.model.domain.PaymentTransaction;
import com.wolfhack.payment.model.entity.PaymentTransactionLog;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentTransactionMapper {
	PaymentTransactionLog toEntity(PaymentTransaction paymentTransaction);

	PaymentTransaction toModel(PaymentTransactionLog paymentTransactionLog);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	PaymentTransactionLog partialUpdate(PaymentTransaction from, @MappingTarget PaymentTransactionLog to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	PaymentTransactionLog update(PaymentTransaction from, @MappingTarget PaymentTransactionLog to);
}