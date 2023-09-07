package com.wolfhack.mapper;

import com.wolfhack.model.domain.EventLog;
import com.wolfhack.model.entity.EventLogEntity;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventLogMapper {
	EventLogEntity toEntity(EventLog inventoryEventLog);

	EventLog toModel(EventLogEntity eventLogEntity);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	EventLogEntity partialUpdate(EventLog from, @MappingTarget EventLogEntity to);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
	EventLogEntity update(EventLog from, @MappingTarget EventLogEntity to);
}