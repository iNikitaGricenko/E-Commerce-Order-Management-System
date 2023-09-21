package com.wolfhack.service;

import com.wolfhack.model.event.EventImpl;
import com.wolfhack.persistence.DefaultEventDatabaseGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final DefaultEventDatabaseGateway defaultEventDatabaseGateway;

    public List<EventImpl> getLatestEvents() {
        return defaultEventDatabaseGateway.getRecentTwentyFive();
    }

    public List<EventImpl> getAllEvents() {
        return defaultEventDatabaseGateway.getAll();
    }

    public Page<EventImpl> getPage(Pageable pageable) {
        return defaultEventDatabaseGateway.getAll(pageable);
    }

}
