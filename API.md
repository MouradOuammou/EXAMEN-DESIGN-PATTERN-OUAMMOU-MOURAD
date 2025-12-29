# Documentation API

## Patterns

### Builder Pattern

#### DocumentBuilder
```java
public class DocumentBuilder {
    public DocumentBuilder withTitle(String title)
    public DocumentBuilder withContent(String content)
    public DocumentBuilder withAuthor(String author)
    public DocumentBuilder withFontSize(int fontSize)
    public DocumentBuilder addHeader()
    public DocumentBuilder addFooter()
    public Document build()
}
```

### Observer Pattern

#### EventManager
```java
public class EventManager {
    public void subscribe(EventListener listener)
    public void unsubscribe(EventListener listener)
    public void notify(Event event)
}

public interface EventListener {
    void update(Event event);
}
```

### Strategy Pattern

#### DataProcessor
```java
public class DataProcessor {
    public void setSortingStrategy(SortingStrategy strategy)
    public <T extends Comparable<T>> void process(List<T> data)
}

public interface SortingStrategy {
    <T extends Comparable<T>> void sort(List<T> items);
}
```

### Adapter Pattern

#### DataSourceAdapter
```java
public class DataSourceAdapter implements ModernDataInterface {
    public List<String> getData()
}
```

## Services

### DocumentService
```java
public class DocumentService {
    public Document createDocument(String title, String author, 
                                  int fontSize, boolean header, boolean footer)
    public List<Document> getAllDocuments()
    public void deleteDocument(Document doc)
    public int getDocumentCount()
}
```

### EventService
```java
public class EventService {
    public EventManager getEventManager(String eventType)
    public void subscribe(String eventType, EventListener listener)
    public void unsubscribe(String eventType, EventListener listener)
    public void fireEvent(String eventType, String data)
    public void registerEventType(String eventType)
}
```

### SortingService
```java
public class SortingService {
    public <T extends Comparable<T>> SortResult<T> 
        sortWithStrategy(List<T> data, String strategyName)
}
```

### DataAdapterService
```java
public class DataAdapterService {
    public String[] getLegacyData()
    public List<String> getModernData()
    public String formatLegacyData(String[] data)
    public String formatModernData(List<String> data)
}
```

## Aspects

### LoggingAspect

Capture tous les appels de méthodes dans:
- Package `com.university.patterns.*`
- Package `com.university.services.*`

Affiche:
- Entrée/sortie de méthodes
- Arguments des méthodes
- Temps d'exécution des services

## AppContext (Singleton)

```java
public class AppContext {
    public static AppContext getInstance()
    public PatternDemoService getPatternDemoService()
}
