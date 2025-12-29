package com.university.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.university.services.PatternDemoService;

public class AppContextTest {
    @Test
    public void testSingletonInstance() {
        AppContext context1 = AppContext.getInstance();
        AppContext context2 = AppContext.getInstance();
        
        assertSame(context1, context2);
    }

    @Test
    public void testGetPatternDemoService() {
        AppContext context = AppContext.getInstance();
        PatternDemoService service = context.getPatternDemoService();
        
        assertNotNull(service);
        assertNotNull(service.getDocumentService());
        assertNotNull(service.getEventService());
        assertNotNull(service.getSortingService());
        assertNotNull(service.getDataAdapterService());
    }

    @Test
    public void testProjectInfo() {
        AppContext context = AppContext.getInstance();
        String info = context.getPatternDemoService().getProjectInfo();
        
        assertTrue(info.contains("Builder"));
        assertTrue(info.contains("Observer"));
        assertTrue(info.contains("Strategy"));
        assertTrue(info.contains("Adapter"));
    }
}
