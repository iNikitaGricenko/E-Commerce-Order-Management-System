package com.wolfhack.model.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventImpl implements Event<Object> {

    private String eventId;

    private Instant createdAt = Instant.now();

    private EventType type = EventType.CREATE;

    private Long dataId;

    private Object data;

}
