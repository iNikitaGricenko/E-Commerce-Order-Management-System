package com.wolfhack.mapper;

import com.wolfhack.model.entity.EventEntity;
import com.wolfhack.model.event.EventImpl;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DefaultEventMapper {


    EventEntity<Object> toEntity(EventImpl event);

    EventImpl toModel(EventEntity<Object> eventEntity);

}
