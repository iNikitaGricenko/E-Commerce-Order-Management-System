package com.wolfhack.payment.mapper;

import com.wolfhack.payment.model.domain.ErrorLog;
import com.wolfhack.payment.model.entity.ErrorLogEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ErrorLogMapper {
	ErrorLogEntity toEntity(ErrorLog errorLog);

	ErrorLog toModel(ErrorLogEntity errorLogEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	ErrorLogEntity partialUpdate(ErrorLog from, @MappingTarget ErrorLogEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	ErrorLogEntity update(ErrorLog from, @MappingTarget ErrorLogEntity to);
}