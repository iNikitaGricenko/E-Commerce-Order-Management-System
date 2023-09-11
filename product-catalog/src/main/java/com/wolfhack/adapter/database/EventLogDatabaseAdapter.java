package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.EventLog;
import org.springframework.stereotype.Service;

@Service
public interface EventLogDatabaseAdapter extends DatabaseAdapter<EventLog> {
}
