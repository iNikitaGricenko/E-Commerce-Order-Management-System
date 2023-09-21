package com.wolfhack.model.event;

import java.io.Serializable;
import java.time.Instant;

public interface Event<T> extends Serializable {

    String getEventId();

    Instant getCreatedAt();

    EventType getType();

    Long getDataId();

    T getData();

}
