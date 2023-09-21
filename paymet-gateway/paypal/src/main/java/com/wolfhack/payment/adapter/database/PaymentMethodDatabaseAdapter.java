package com.wolfhack.payment.adapter.database;

import com.wolfhack.payment.model.domain.PaymentMethod;
import org.springframework.stereotype.Service;

@Service
public interface PaymentMethodDatabaseAdapter extends DatabaseAdapter<PaymentMethod> {
}
