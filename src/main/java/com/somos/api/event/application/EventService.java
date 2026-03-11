package com.somos.api.event.application;

public class EventService {
    public final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
