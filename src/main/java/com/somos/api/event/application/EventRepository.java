package com.somos.api.event.application;

import com.somos.api.event.domain.Event;

import java.util.Optional;

public interface EventRepository {
    public Optional<Event> findById(String id);
    public void save(Event event);
}
