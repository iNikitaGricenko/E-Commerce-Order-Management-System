package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.UserToken;

public interface UserTokenDatabaseAdapter extends DatabaseAdapter<UserToken> {

	UserToken getByToken(String token);

	UserToken getByUserId(Long id);

	boolean existsByUserId(Long userId);
}
