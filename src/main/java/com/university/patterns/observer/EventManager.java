package com.university.patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<EventListener> listeners = new ArrayList<>();
    private final String eventType;

    public EventManager(String eventType) {
        this.eventType = eventType;
    }

    public void subscribe(EventListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    public void notify(Event event) {
        System.out.println("EventManager: " + this.eventType + " event notification");
        for (EventListener listener : listeners) {
            listener.update(event);
        }
    }

    public static class Event {
        public String data;
        public Event(String data) {
            this.data = data;
        }
    }
}
