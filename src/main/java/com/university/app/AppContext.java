package com.university.app;

import com.university.services.PatternDemoService;

public class AppContext {
    private static AppContext instance;
    private final PatternDemoService patternDemoService;

    private AppContext() {
        this.patternDemoService = new PatternDemoService();
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public PatternDemoService getPatternDemoService() {
        return patternDemoService;
    }
}
