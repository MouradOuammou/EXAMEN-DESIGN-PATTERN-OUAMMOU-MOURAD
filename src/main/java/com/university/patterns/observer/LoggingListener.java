package com.university.patterns.observer;

public class LoggingListener implements EventListener {
    @Override
    public void update(EventManager.Event event) {
        System.out.println("LoggingListener: Event received with data: " + event.data);
    }
}
