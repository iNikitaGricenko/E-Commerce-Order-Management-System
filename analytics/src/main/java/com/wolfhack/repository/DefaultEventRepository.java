package com.wolfhack.repository;

import com.wolfhack.model.entity.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

public interface DefaultEventRepository extends MongoRepository<EventEntity<Object>, String> {

    List<EventEntity<Object>> findAllByCreatedAtBetween(Instant after, Instant before);

    List<EventEntity<Object>> findTop5ByOrderByCreatedAt();

    List<EventEntity<Object>> findTop10ByOrderByCreatedAt();

    List<EventEntity<Object>> findTop25ByOrderByCreatedAt();

    List<EventEntity<Object>> findTop50ByOrderByCreatedAt();

    List<EventEntity<Object>> findTop100ByOrderByCreatedAt();

}
