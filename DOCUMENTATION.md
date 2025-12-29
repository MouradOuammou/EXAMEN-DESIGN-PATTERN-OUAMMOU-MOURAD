# Documentation du Projet Design Patterns

## Table des matières
1. [Vue d'ensemble](#vue-densemble)
2. [Architecture](#architecture)
3. [Installation](#installation)
4. [Utilisation](#utilisation)
5. [Design Patterns](#design-patterns)
6. [Tests](#tests)
7. [AspectJ](#aspectj)

## Vue d'ensemble

Ce projet est une application JavaFX démontrant 5 design patterns majeurs en Java:
- **Builder Pattern** : Construction flexible d'objets complexes
- **Observer Pattern** : Gestion des événements et notificationsObserver
- **Strategy Pattern** : Sélection dynamique d'algorithmes
- **Adapter Pattern** : Adaptation d'interfaces incompatibles
- **AspectJ** : Programmation orientée aspects pour logging transversal

## Architecture

### Structure des packages

```
com.university/
├── app/
│   ├── Main.java
│   ├── AppContext.java
│   ├── controller/
│   │   ├── BuilderController.java
│   │   ├── ObserverController.java
│   │   ├── StrategyController.java
│   │   └── AdapterController.java
│   └── view/
│       └── MainWindow.java
├── patterns/
│   ├── builder/
│   │   └── DocumentBuilder.java
│   ├── observer/
│   │   ├── EventManager.java
│   │   ├── EventListener.java
│   │   └── LoggingListener.java
│   ├── strategy/
│   │   ├── SortingStrategy.java
│   │   ├── BubbleSort.java
│   │   ├── QuickSort.java
│   │   └── DataProcessor.java
│   └── adapter/
│       ├── LegacyDataSource.java
│       ├── ModernDataInterface.java
│       └── DataSourceAdapter.java
├── services/
│   ├── DocumentService.java
│   ├── EventService.java
│   ├── SortingService.java
│   ├── DataAdapterService.java
│   └── PatternDemoService.java
└── aspects/
    └── LoggingAspect.java
```

### Diagramme de classes principal

```
EventManager <|-- EventListener
DataProcessor *-- SortingStrategy
SortingStrategy <|.. BubbleSort
SortingStrategy <|.. QuickSort
ModernDataInterface <|.. DataSourceAdapter
DataSourceAdapter --> LegacyDataSource
DocumentBuilder --> Document
PatternDemoService --> DocumentService
PatternDemoService --> EventService
PatternDemoService --> SortingService
PatternDemoService --> DataAdapterService
```

## Installation

### Prérequis

- Java Development Kit (JDK) 17 ou supérieur
- Apache Maven 3.6 ou supérieur
- JavaFX 21

### Étapes d'installation

```bash
# 1. Cloner le projet
git clone <repository-url>
cd design-patterns-project

# 2. Compiler le projet
mvn clean compile

# 3. Exécuter les tests
mvn test

# 4. Lancer l'application
mvn javafx:run

# 5. Générer le JAR exécutable
mvn package
```

## Utilisation

### Lancer l'application

```bash
mvn javafx:run
```

L'application s'ouvrira avec une interface à 4 onglets, un pour chaque pattern.

### Interface utilisateur

#### Onglet Builder
- Entrez le titre du document
- Entrez l'auteur
- Sélectionnez la taille de police
- Cochez les options pour ajouter un en-tête et/ou un pied de page
- Cliquez sur "Construire le document"
- Visionnez le résultat dans la zone de texte

#### Onglet Observer
- Cliquez sur "S'abonner" pour créer un listener
- Entrez des données dans le champ texte
- Cliquez sur "Déclencher l'événement" pour notifier les listeners
- Visionnez les notifications dans le journal des événements
- Cliquez sur "Se désabonner" pour arrêter d'écouter

#### Onglet Strategy
- Entrez des données à trier séparées par des virgules, ou cliquez sur "Générer données aléatoires"
- Sélectionnez une stratégie de tri (Bubble Sort ou Quick Sort)
- Cliquez sur "Trier"
- Visionnez les données triées et le temps d'exécution

#### Onglet Adapter
- Cliquez sur "Récupérer données (Legacy)" pour voir les données du système legacy
- Cliquez sur "Adapter et afficher" pour adapter les données et les afficher dans différents formats
- Visionnez la ListView qui affiche les données adaptées

## Design Patterns

### 1. Builder Pattern

**Objectif** : Construire des objets complexes étape par étape.

**Classe principale** : `DocumentBuilder`

**Exemple d'utilisation** :
```java
DocumentBuilder.Document doc = new DocumentBuilder()
    .withTitle("Mon Document")
    .withAuthor("Jean Dupont")
    .withFontSize(12)
    .addHeader()
    .addFooter()
    .build();
```

**Avantages** :
- Sépare la construction de la représentation
- Rend le code plus lisible et maintenable
- Permet des constructions flexibles et partielles

### 2. Observer Pattern

**Objectif** : Notifier plusieurs objets des changements d'état.

**Classe principale** : `EventManager`, `EventListener`

**Exemple d'utilisation** :
```java
EventManager eventManager = new EventManager("UserAction");
eventManager.subscribe(new LoggingListener());
eventManager.notify(new EventManager.Event("User logged in"));
```

**Avantages** :
- Couplage faible entre publisher et subscribers
- Permet une communication asynchrone
- Facile d'ajouter ou retirer des listeners

### 3. Strategy Pattern

**Objectif** : Définir une famille d'algorithmes et les rendre interchangeables.

**Classe principale** : `DataProcessor`, `SortingStrategy`

**Implémentations** : `BubbleSort`, `QuickSort`

**Exemple d'utilisation** :
```java
DataProcessor processor = new DataProcessor();
processor.setSortingStrategy(new QuickSort());
processor.process(data);
```

**Avantages** :
- Permet de changer l'algorithme à l'exécution
- Élimine les conditions complexes
- Facilite l'ajout de nouvelles stratégies

### 4. Adapter Pattern

**Objectif** : Adapter une interface incompatible à une autre.

**Classe principale** : `DataSourceAdapter`

**Exemple d'utilisation** :
```java
LegacyDataSource legacy = new LegacyDataSource();
ModernDataInterface adapter = new DataSourceAdapter(legacy);
List<String> modernData = adapter.getData();
```

**Avantages** :
- Permet l'interopérabilité entre codes legacy et nouveau
- Sépare les responsabilités
- Facilite la transition vers de nouvelles interfaces

### 5. AspectJ - Programmation Orientée Aspects

**Objectif** : Extraire les préoccupations transversales (logging, sécurité, performance).

**Classe principale** : `LoggingAspect`

**Pointcuts** :
- `patternMethods()` : Tous les appels de méthodes dans le package `patterns`
- `serviceMethods()` : Tous les appels de méthodes dans le package `services`

**Advices implémentés** :
- `@Before` : Logging avant l'exécution
- `@After` : Logging après l'exécution
- `@Around` : Mesure du temps d'exécution
- `@AfterReturning` : Logging après un retour réussi

**Avantages** :
- Centralise le code de logging
- Réduit la duplication de code
- Facilite la maintenance et les modifications futures

## Tests

### Exécuter les tests

```bash
mvn test
```

### Couverture de code

```bash
mvn jacoco:report
```

### Classes de test

1. **DocumentBuilderTest** : Test la construction de documents
2. **EventManagerTest** : Test les subscriptions et notifications
3. **DataProcessorTest** : Test les stratégies de tri
4. **DataSourceAdapterTest** : Test l'adaptation d'interfaces
5. **DocumentServiceTest** : Test le service de documents
6. **SortingServiceTest** : Test le service de tri
7. **EventServiceTest** : Test le service d'événements
8. **AppContextTest** : Test le contexte singleton

## AspectJ

### Configuration Maven

AspectJ est configuré dans le pom.xml avec le plugin `aspectj-maven-plugin`:

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>aspectj-maven-plugin</artifactId>
    <version>1.14.0</version>
    <configuration>
        <source>17</source>
        <target>17</target>
        <complianceLevel>17</complianceLevel>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>compile</goal>
                <goal>test-compile</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

### Observations du logging AspectJ

Lors de l'exécution, vous verrez des messages comme:
```
[ASPECT] ENTRY: DocumentBuilder.build()
[ASPECT] Arguments: [title, author]
[ASPECT] EXIT: DocumentBuilder.build()
[ASPECT] DocumentService.createDocument() executed in 2 ms
```

## Déploiement

### Créer un JAR exécutable

```bash
mvn clean package
java -jar target/design-patterns-project-1.0.0.jar
```

### Variables d'environnement

Aucune variable d'environnement n'est requise pour ce projet.

## Troubleshooting

### Problème: "Module javafx.base not found"

**Solution** : Assurez-vous que JavaFX est correctement configuré dans le pom.xml.

### Problème: "Aspect not found"

**Solution** : Vérifiez que le plugin aspectj-maven-plugin est correctement configuré et que AspectJ est dans les dépendances.

### Problème: Tests ne s'exécutent pas

**Solution** : Compilez d'abord avec `mvn clean compile` avant de lancer les tests.

## Contributions

Pour contribuer à ce projet:
1. Forkez le repository
2. Créez une branche (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Pushez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## Licence

Ce projet est fourni à titre éducatif.
```

```latex file="rapport/chapters/3-conception.tex"
\chapter{Conception du système}

\section{Architecture générale}

Le système est organisé en plusieurs couches:

\begin{itemize}
    \item \textbf{Couche présentation} : Interface JavaFX avec contrôleurs
    \item \textbf{Couche métier} : Patterns et services implémentés
    \item \textbf{Couche aspects} : Logging transversal avec AspectJ
\end{itemize}

\section{Diagramme d'architecture}

\begin{figure}[H]
    \centering
    \begin{verbatim}
    ┌─────────────────────────────────────┐
    │      Couche Présentation (UI)       │
    │      (JavaFX MainWindow)             │
    └──────────────┬──────────────────────┘
                   │
    ┌──────────────▼──────────────────────┐
    │    Couche Métier (Controllers)      │
    │ (Builder, Observer, Strategy, etc) │
    └──────────────┬──────────────────────┘
                   │
    ┌──────────────▼──────────────────────┐
    │      Couche Services                │
    │ (Document, Event, Sorting Services) │
    └──────────────┬──────────────────────┘
                   │
    ┌──────────────▼──────────────────────┐
    │    Design Patterns & Aspects        │
    │ (Builder, Observer, Strategy,       │
    │  Adapter, AspectJ Logging)         │
    └─────────────────────────────────────┘
    \end{verbatim}
    \caption{Architecture en couches du système}
    \label{fig:architecture}
\end{figure}

\section{Design Patterns utilisés}

\subsection{Builder Pattern}

Le Builder Pattern est utilisé pour la construction de documents avec options flexibles.

\begin{table}[H]
    \centering
    \caption{Structure du Builder Pattern}
    \label{tab:builder-structure}
    \begin{tabular}{|l|l|}
    \hline
    \textbf{Classe} & \textbf{Responsabilité} \\
    \hline
    DocumentBuilder & Constructeur fluide \\
    \hline
    DocumentBuilder.Document & Objet construit \\
    \hline
    \end{tabular}
\end{table}

\subsection{Observer Pattern}

Le pattern Observer permet la notification d'événements à plusieurs listeners.

\begin{verbatim}
EventManager
    ├─ subscribe(listener)
    ├─ unsubscribe(listener)
    └─ notify(event)
       └─ EventListener.update()
\end{verbatim}

\subsection{Strategy Pattern}

Le Strategy Pattern permet de sélectionner l'algorithme de tri à l'exécution.

\begin{verbatim}
DataProcessor
    └─ setSortingStrategy(strategy)
       ├─ BubbleSort.sort()
       └─ QuickSort.sort()
\end{verbatim}

\subsection{Adapter Pattern}

L'Adapter Pattern adapte l'interface legacy à l'interface moderne.

\begin{verbatim}
DataSourceAdapter implements ModernDataInterface
    └─ getData()
       └─ LegacyDataSource.retrieveData()
\end{verbatim}

\section{Tableau récapitulatif des patterns}

\begin{table}[H]
    \centering
    \caption{Résumé complet des design patterns}
    \label{tab:patterns-summary}
    \begin{tabular}{|l|l|p{4cm}|l|}
    \hline
    \textbf{Pattern} & \textbf{Classe} & \textbf{Responsabilité} & \textbf{Type} \\
    \hline
    Builder & DocumentBuilder & Construction flexible & Créationnel \\
    \hline
    Observer & EventManager & Gestion d'événements & Comportement \\
    \hline
    Strategy & DataProcessor & Sélection d'algorithme & Comportement \\
    \hline
    Adapter & DataSourceAdapter & Adaptation d'interface & Structurel \\
    \hline
    AspectJ & LoggingAspect & Logging transversal & Structurel \\
    \hline
    \end{tabular}
\end{table}

\section{Services implémentés}

\subsection{Service Layer}

Les services fournissent une abstraction sur les patterns:

\begin{table}[H]
    \centering
    \caption{Services implémentés}
    \label{tab:services}
    \begin{tabular}{|l|p{6cm}|}
    \hline
    \textbf{Service} & \textbf{Fonctionnalité} \\
    \hline
    DocumentService & Création et gestion de documents \\
    \hline
    EventService & Gestion centralisée des événements \\
    \hline
    SortingService & Tri avec mesure de performance \\
    \hline
    DataAdapterService & Adaptation de sources de données \\
    \hline
    PatternDemoService & Façade regroupant tous les services \\
    \hline
    \end{tabular}
\end{table}

\section{Context Singleton}

\texttt{AppContext} est implémenté comme un singleton pour donner accès aux services depuis n'importe où dans l'application:

\begin{lstlisting}[language=Java]
AppContext context = AppContext.getInstance();
PatternDemoService service = context.getPatternDemoService();
\end{lstlisting}

\section{Aspects AspectJ}

L'aspect LoggingAspect capture les appels de méthodes et mesure les performances:

\begin{table}[H]
    \centering
    \caption{Advices AspectJ implémentés}
    \label{tab:aspects}
    \begin{tabular}{|l|l|l|}
    \hline
    \textbf{Advice} & \textbf{Pointcut} & \textbf{Action} \\
    \hline
    @Before & patternMethods() & Logging d'entrée \\
    \hline
    @AfterReturning & patternMethods() & Logging de sortie \\
    \hline
    @Around & serviceMethods() & Mesure de temps \\
    \hline
    \end{tabular}
\end{table}
