package com.wolfhack.mapper;

import com.wolfhack.model.entity.EventEntity;
import com.wolfhack.model.event.Event;

public interface EventMapper<T> {

    EventEntity<T> toEntity(Event<T> event);

    Event<T> toModel(EventEntity<T> eventEntity);

}
