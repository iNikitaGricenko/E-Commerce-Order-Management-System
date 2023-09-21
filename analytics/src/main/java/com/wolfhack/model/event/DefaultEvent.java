package com.wolfhack.model.event;

import com.wolfhack.model.entity.EventEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefaultEvent<T> implements Event<T> {

    private String eventId;

    private Instant createdAt = Instant.now();

    private EventType type = EventType.CREATE;

    private Long dataId;

    private T data;

    public EventEntity<T> toEntity() {
        return new EventEntity<>(type, data, dataId);
    }

}
