package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.User;

public interface UserDatabaseAdapter extends DatabaseAdapter<User> {

	User getByUsername(String username);

	User getByEmail(String email);
}
