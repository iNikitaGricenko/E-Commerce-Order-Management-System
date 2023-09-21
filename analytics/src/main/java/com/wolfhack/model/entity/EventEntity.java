package com.wolfhack.model.entity;

import com.wolfhack.model.event.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;
import java.util.UUID;

@Data
@SuperBuilder
@Document("event")
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity<T> {

    @MongoId
    private String eventId;

    @Builder.Default
    private Instant createdAt = Instant.now();

    @Builder.Default
    private EventType type = EventType.CREATE;

    private Long dataId;

    private T data;

    public EventEntity(T data) {
        this.data = data;
    }

    public EventEntity(EventType type, T data, Long dataId) {
        this.type = type;
        this.data = data;
        this.dataId = dataId;
    }
}
