package com.wolfhack.payment.adapter.database;

import com.wolfhack.payment.model.domain.PaymentTransaction;
import org.springframework.stereotype.Service;

@Service
public interface PaymentTransactionDatabaseAdapter extends DatabaseAdapter<PaymentTransaction> {
}
