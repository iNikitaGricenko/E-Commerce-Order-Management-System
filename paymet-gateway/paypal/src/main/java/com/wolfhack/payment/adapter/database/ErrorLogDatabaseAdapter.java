package com.wolfhack.payment.adapter.database;

import com.wolfhack.payment.model.domain.ErrorLog;
import org.springframework.stereotype.Service;

@Service
public interface ErrorLogDatabaseAdapter extends DatabaseAdapter<ErrorLog> {
}
