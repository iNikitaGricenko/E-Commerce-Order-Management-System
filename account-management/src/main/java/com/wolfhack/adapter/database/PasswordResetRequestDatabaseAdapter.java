package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.PasswordResetRequest;

public interface PasswordResetRequestDatabaseAdapter extends DatabaseAdapter<PasswordResetRequest> {

	PasswordResetRequest getByToken(String token);

	PasswordResetRequest getByUserId(Long id);
}
