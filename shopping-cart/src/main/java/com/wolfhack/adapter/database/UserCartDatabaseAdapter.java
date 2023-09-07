package com.wolfhack.adapter.database;

import com.wolfhack.model.domain.UserCart;

public interface UserCartDatabaseAdapter extends DatabaseAdapter<UserCart> {

	UserCart getByUser(Long userId);

}
