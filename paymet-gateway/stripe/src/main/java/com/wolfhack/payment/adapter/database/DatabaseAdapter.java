package com.wolfhack.payment.adapter.database;

import com.wolfhack.payment.model.domain.DomainModel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface DatabaseAdapter<T extends DomainModel> {

	Long save(T model);

	Long partialUpdate(Long id, T model);

	Long update(Long id, T model);

	T getById(Long id);

	boolean exists(Long id);

	Collection<T> getById(Collection<Long> ids);

	List<T> getAll();

	void delete(Long id);
}
