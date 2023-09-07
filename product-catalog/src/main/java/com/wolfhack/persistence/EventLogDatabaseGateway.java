package com.wolfhack.persistence;

import com.wolfhack.adapter.database.EventLogDatabaseAdapter;
import com.wolfhack.exception.NotFoundException;
import com.wolfhack.mapper.EventLogMapper;
import com.wolfhack.model.domain.EventLog;
import com.wolfhack.model.entity.EventLogEntity;
import com.wolfhack.repository.EventLogRepository;
import com.wolfhack.wrapper.DomainPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventLogDatabaseGateway implements EventLogDatabaseAdapter {

	private final EventLogRepository eventLogRepository;
	private final EventLogMapper eventLogMapper;

	@Override
	public Long save(EventLog model) {
		EventLogEntity entity = eventLogMapper.toEntity(model);
		return eventLogRepository.save(entity).getId();
	}

	@Override
	public Long partialUpdate(Long id, EventLog model) {
		EventLogEntity updated = eventLogRepository.findById(id)
				.map(categoryEntity -> eventLogMapper.partialUpdate(model, categoryEntity))
				.orElseThrow(NotFoundException::new);

		return eventLogRepository.save(updated).getId();
	}

	@Override
	public Long update(Long id, EventLog model) {
		if (!exists(id)) {
			throw new NotFoundException("Event log does not exist");
		}
		EventLogEntity entity = eventLogMapper.toEntity(model);
		eventLogMapper.update(model, entity);
		return eventLogRepository.save(entity).getId();
	}

	@Override
	public EventLog getById(Long id) {
		return eventLogRepository.findById(id).map(eventLogMapper::toModel).orElseThrow(NotFoundException::new);
	}

	@Override
	public boolean exists(Long id) {
		return eventLogRepository.existsById(id);
	}

	@Override
	public Collection<EventLog> getById(Collection<Long> ids) {
		return eventLogRepository.findAllById(ids).stream().map(eventLogMapper::toModel).toList();
	}

	@Override
	public List<EventLog> getAll() {
		return eventLogRepository.findAll().stream().map(eventLogMapper::toModel).toList();
	}

	@Override
	public DomainPage<EventLog> getPage(Pageable pageable) {
		Page<EventLog> page = eventLogRepository.findAll(pageable).map(eventLogMapper::toModel);
		return new DomainPage<>(page);
	}

	@Override
	public void delete(Long id) {
		eventLogRepository.deleteById(id);
	}
}
