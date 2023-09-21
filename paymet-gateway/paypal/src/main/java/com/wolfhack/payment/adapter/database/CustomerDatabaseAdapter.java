package com.wolfhack.payment.adapter.database;

import com.wolfhack.payment.model.domain.CustomerInformation;
import org.springframework.stereotype.Service;

@Service
public interface CustomerDatabaseAdapter extends DatabaseAdapter<CustomerInformation> {
	CustomerInformation getByUserId(Long userId);
}
