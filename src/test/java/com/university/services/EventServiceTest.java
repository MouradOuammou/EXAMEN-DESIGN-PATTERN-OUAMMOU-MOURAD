package com.university.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import com.university.patterns.observer.EventListener;
import com.university.patterns.observer.EventManager;

public class EventServiceTest {
    private EventService eventService;
    private TestEventListener testListener;

    @BeforeEach
    public void setUp() {
        eventService = new EventService();
        testListener = new TestEventListener();
    }

    @Test
    public void testGetEventManager() {
        EventManager manager = eventService.getEventManager("TestEvent");
        assertNotNull(manager);
    }

    @Test
    public void testSubscribeToEvent() {
        eventService.registerEventType("UserEvent");
        eventService.subscribe("UserEvent", testListener);
        
        assertNotNull(testListener);
    }

    @Test
    public void testFireEvent() {
        eventService.registerEventType("DataEvent");
        eventService.subscribe("DataEvent", testListener);
        eventService.fireEvent("DataEvent", "test data");
        
        assertEquals("test data", testListener.getLastData());
    }

    private static class TestEventListener implements EventListener {
        private String lastData;

        @Override
        public void update(EventManager.Event event) {
            this.lastData = event.data;
        }

        public String getLastData() {
            return lastData;
        }
    }
}
