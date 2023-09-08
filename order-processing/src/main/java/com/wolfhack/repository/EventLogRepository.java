package com.wolfhack.repository;

import com.wolfhack.model.entity.EventLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLogEntity, Long> {
}