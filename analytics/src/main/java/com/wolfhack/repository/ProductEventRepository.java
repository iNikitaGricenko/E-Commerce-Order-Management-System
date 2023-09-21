package com.wolfhack.repository;

import com.wolfhack.model.domain.Product;
import com.wolfhack.model.entity.EventEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductEventRepository extends MongoRepository<EventEntity<Product>, String> {

    EventEntity<Product> findByDataId(Long dataId);

}
