package com.wolfhack.payment.adapter.database;

import com.wolfhack.payment.model.domain.PaymentLog;
import org.springframework.stereotype.Service;

@Service
public interface PaymentLogDatabaseAdapter extends DatabaseAdapter<PaymentLog> {
}
