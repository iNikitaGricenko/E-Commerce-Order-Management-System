package com.wolfhack.repository;

import com.wolfhack.model.domain.User;
import com.wolfhack.model.entity.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEventRepository extends MongoRepository<EventEntity<User>, String> {
}
