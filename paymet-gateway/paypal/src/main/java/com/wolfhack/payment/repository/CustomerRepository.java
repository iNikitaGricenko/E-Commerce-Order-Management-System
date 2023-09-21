package com.wolfhack.payment.repository;

import com.wolfhack.payment.model.entity.CustomerInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerInformationEntity, Long> {
	Optional<CustomerInformationEntity> findByUserId(Long userId);
}