package com.university.patterns.observer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class EventManagerTest {
    private EventManager eventManager;
    private TestListener testListener;

    @BeforeEach
    public void setUp() {
        eventManager = new EventManager("TestEvent");
        testListener = new TestListener();
    }

    @Test
    public void testSubscribe() {
        eventManager.subscribe(testListener);
        assertNotNull(testListener);
    }

    @Test
    public void testEventNotification() {
        eventManager.subscribe(testListener);
        EventManager.Event event = new EventManager.Event("test data");
        
        eventManager.notify(event);
        
        assertEquals("test data", testListener.getLastEventData());
    }

    @Test
    public void testUnsubscribe() {
        eventManager.subscribe(testListener);
        eventManager.unsubscribe(testListener);
        
        EventManager.Event event = new EventManager.Event("test");
        eventManager.notify(event);
        
        assertNull(testListener.getLastEventData());
    }

    // Helper class for testing
    private static class TestListener implements EventListener {
        private String lastEventData;

        @Override
        public void update(EventManager.Event event) {
            this.lastEventData = event.data;
        }

        public String getLastEventData() {
            return lastEventData;
        }
    }
}
