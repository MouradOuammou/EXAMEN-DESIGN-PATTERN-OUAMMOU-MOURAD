package com.university.services;

import com.university.patterns.observer.EventManager;
import com.university.patterns.observer.EventListener;
import java.util.HashMap;
import java.util.Map;

public class EventService {
    private final Map<String, EventManager> eventManagers = new HashMap<>();

    public EventManager getEventManager(String eventType) {
        return eventManagers.computeIfAbsent(eventType, EventManager::new);
    }

    public void subscribe(String eventType, EventListener listener) {
        getEventManager(eventType).subscribe(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        getEventManager(eventType).unsubscribe(listener);
    }

    public void fireEvent(String eventType, String data) {
        EventManager.Event event = new EventManager.Event(data);
        getEventManager(eventType).notify(event);
    }

    public void registerEventType(String eventType) {
        eventManagers.put(eventType, new EventManager(eventType));
    }
}
