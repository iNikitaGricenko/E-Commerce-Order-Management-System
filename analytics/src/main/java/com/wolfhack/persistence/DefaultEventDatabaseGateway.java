package com.wolfhack.persistence;

import com.wolfhack.mapper.DefaultEventMapper;
import com.wolfhack.model.event.EventImpl;
import com.wolfhack.repository.DefaultEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultEventDatabaseGateway {

    private final DefaultEventRepository defaultEventRepository;
    private final DefaultEventMapper defaultEventMapper;

    public List<EventImpl> getRecentFive() {
        return defaultEventRepository.findTop5ByOrderByCreatedAt().stream()
            .map(defaultEventMapper::toModel)
            .toList();
    }

    public List<EventImpl> getRecentTen() {
        return defaultEventRepository.findTop10ByOrderByCreatedAt().stream()
            .map(defaultEventMapper::toModel)
            .toList();
    }

    public List<EventImpl> getRecentTwentyFive() {
        return defaultEventRepository.findTop25ByOrderByCreatedAt().stream()
            .map(defaultEventMapper::toModel)
            .toList();
    }

    public List<EventImpl> getRecentFifteen() {
        return defaultEventRepository.findTop50ByOrderByCreatedAt().stream()
            .map(defaultEventMapper::toModel)
            .toList();
    }

    public List<EventImpl> getRecentHundred() {
        return defaultEventRepository.findTop100ByOrderByCreatedAt().stream()
            .map(defaultEventMapper::toModel)
            .toList();
    }

    public List<EventImpl> getAll() {
        return defaultEventRepository.findAll().stream()
            .map(defaultEventMapper::toModel)
            .toList();
    }

    public Page<EventImpl> getAll(Pageable pageable) {
        return defaultEventRepository.findAll(pageable)
            .map(defaultEventMapper::toModel);
    }

}
