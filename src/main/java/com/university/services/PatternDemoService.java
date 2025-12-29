package com.university.services;

public class PatternDemoService {
    private final DocumentService documentService;
    private final EventService eventService;
    private final SortingService sortingService;
    private final DataAdapterService dataAdapterService;

    public PatternDemoService() {
        this.documentService = new DocumentService();
        this.eventService = new EventService();
        this.sortingService = new SortingService();
        this.dataAdapterService = new DataAdapterService();
    }

    public DocumentService getDocumentService() {
        return documentService;
    }

    public EventService getEventService() {
        return eventService;
    }

    public SortingService getSortingService() {
        return sortingService;
    }

    public DataAdapterService getDataAdapterService() {
        return dataAdapterService;
    }

    public String getProjectInfo() {
        return """
                Projet Design Patterns en Java
                ================================
                
                Patterns implémentés:
                1. Builder Pattern - Construction flexible d'objets
                2. Observer Pattern - Gestion d'événements
                3. Strategy Pattern - Sélection d'algorithmes
                4. Adapter Pattern - Adaptation d'interfaces
                5. AspectJ - Logging transversal
                
                Technologie: JavaFX 21, Java 17+, Maven, JUnit 5
                """;
    }
}
