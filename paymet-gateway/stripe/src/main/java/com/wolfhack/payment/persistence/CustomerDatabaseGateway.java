package com.wolfhack.payment.persistence;

import com.wolfhack.payment.adapter.database.CustomerDatabaseAdapter;
import com.wolfhack.payment.exception.NotFoundException;
import com.wolfhack.payment.mapper.CustomerMapper;
import com.wolfhack.payment.model.domain.CustomerInformation;
import com.wolfhack.payment.model.entity.CustomerInformationEntity;
import com.wolfhack.payment.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomerDatabaseGateway implements CustomerDatabaseAdapter {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	@Override
	public Long save(CustomerInformation model) {
		CustomerInformationEntity entity = customerMapper.toEntity(model);
		return customerRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, CustomerInformation model) {
		CustomerInformationEntity updated = customerRepository.findById(id)
				.map(categoryEntity -> customerMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return customerRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, CustomerInformation model) {
		if (!exists(id)) {
			throw new NotFoundException("Customer does not exist");
		}
		CustomerInformationEntity entity = customerMapper.toEntity(model);
		customerMapper.update(model, entity);
		return customerRepository.save(entity).getId();
	}

	@Override
	public CustomerInformation getById(Long id) {
		return customerRepository.findById(id).map(customerMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return customerRepository.existsById(id);
	}

	@Override
	public Collection<CustomerInformation> getById(Collection<Long> ids) {
		return customerRepository.findAllById(ids).stream().map(customerMapper::toModel).toList();
	}

	@Override
	public List<CustomerInformation> getAll() {
		return customerRepository.findAll().stream().map(customerMapper::toModel).toList();
	}

	@Override
	public void delete(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	public CustomerInformation getByUserId(Long userId) {
		return customerRepository.findByUserId(userId).map(customerMapper::toModel).orElseThrow(NotFoundException::new);
	}
}
