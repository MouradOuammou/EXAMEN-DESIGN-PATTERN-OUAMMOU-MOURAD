# Guide de compilation et d'exécution

## Structure du projet

```
design-patterns-project/
├── pom.xml                      # Configuration Maven
├── src/
│   ├── main/java/com/university/
│   │   ├── app/                 # Application principale
│   │   ├── patterns/            # Design patterns
│   │   ├── services/            # Services métier
│   │   └── aspects/             # Aspects AspectJ
│   └── test/java/com/university/# Tests unitaires
├── rapport/                      # Rapport LaTeX
├── README.md                     # Vue d'ensemble
├── DOCUMENTATION.md             # Documentation complète
├── DEVELOPMENT.md               # Guide de développement
├── API.md                        # Référence API
└── BUILD.md                      # Ce fichier
```

## Compilation

### Option 1: Compilation complète

```bash
mvn clean compile
```

**Explications:**
- `clean` : Supprime les fichiers générés précédemment
- `compile` : Compile le code source

### Option 2: Compilation avec AspectJ

```bash
mvn clean compile -X
```

Le flag `-X` affiche les logs détaillés du compilateur AspectJ.

## Tests

### Exécuter tous les tests

```bash
mvn test
```

### Exécuter un test spécifique

```bash
mvn test -Dtest=DocumentBuilderTest
```

### Exécuter une méthode de test spécifique

```bash
mvn test -Dtest=DocumentBuilderTest#testBuildDocument
```

### Avec rapport de couverture de code

```bash
mvn test jacoco:report
```

Les résultats se trouvent dans `target/site/jacoco/index.html`

## Exécution

### Lancer l'application JavaFX

```bash
mvn javafx:run
```

L'application s'ouvrira dans une fenêtre avec 4 onglets:
- **Builder** : Démonstration du Builder Pattern
- **Observer** : Démonstration du Observer Pattern
- **Strategy** : Démonstration du Strategy Pattern
- **Adapter** : Démonstration du Adapter Pattern

### Lancer avec arguments (optionnel)

```bash
mvn javafx:run -Dapp.args="--debug"
```

## Empaquetage

### Créer un JAR exécutable

```bash
mvn package
```

Cela génère un fichier `target/design-patterns-project-1.0.0.jar`

### Exécuter le JAR

```bash
java -jar target/design-patterns-project-1.0.0.jar
```

### Créer une distribution avec dépendances

```bash
mvn assembly:assembly
```

## Génération de documentation

### Documentation JavaDoc

```bash
mvn javadoc:javadoc
```

Résultat dans `target/site/apidocs/index.html`

### Rapport Maven

```bash
mvn site
```

Résultat dans `target/site/index.html`

## Rapport LaTeX

### Compiler le rapport en PDF

```bash
cd rapport
pdflatex -interaction=nonstopmode main.tex
bibtex main
pdflatex -interaction=nonstopmode main.tex
pdflatex -interaction=nonstopmode main.tex
```

Résultat : `rapport/main.pdf`

### Ou avec un makefile (si disponible)

```bash
cd rapport
make
```

## Configuration IDE

### IntelliJ IDEA

1. File → Open → Sélectionnez le répertoire du projet
2. Attendez l'indexation complète
3. Run → Edit Configurations
4. Ajoutez une nouvelle configuration de type "Maven"
5. Dans Command line, entrez: `javafx:run`
6. Cliquez sur Run ou Debug

### Eclipse

1. File → Import → Existing Maven Projects
2. Sélectionnez le répertoire du projet
3. Maven téléchargera les dépendances
4. Right-click sur le projet → Run As → Maven build
5. Entrez `javafx:run` dans Goals

### VS Code

1. Installez les extensions:
   - Extension Pack for Java
   - Maven for Java
   - Debugger for Java

2. Ouvrez le répertoire du projet
3. La Vue Java Explorer affichera le projet
4. Utilisez le terminal intégré pour les commandes Maven

## Troubleshooting

### Erreur: "JAVA_HOME is not set"

```bash
# Linux/Mac
export JAVA_HOME=/path/to/jdk17
export PATH=$JAVA_HOME/bin:$PATH

# Windows (Command Prompt)
set JAVA_HOME=C:\path\to\jdk17
set PATH=%JAVA_HOME%\bin;%PATH%
```

### Erreur: "Module javafx.base not found"

```bash
# Vérifiez que JavaFX est dans le pom.xml
mvn dependency:tree | grep javafx
```

### Erreur: "Aspect not compiled"

```bash
mvn clean compile
```

L'option `clean` est importante pour réinitialiser les fichiers AspectJ.

### L'application JavaFX ne démarre pas

```bash
# Essayez avec le log debug
mvn javafx:run -X
```

### Tests échouent avec "No matching strategy"

La stratégie doit être en minuscules (`"bubble"` ou `"quick"`).

## Performance

### Compiler plus rapidement (offline)

```bash
mvn -o clean compile
```

### Passer certains tests

```bash
mvn test -DskipTests
```

### Compiler une seule classe

```bash
mvn compile -Dmaven.compiler.includes="**/DocumentBuilder.java"
```

## Nettoyage

### Supprimer tous les fichiers générés

```bash
mvn clean
```

### Supprimer le cache Maven local

```bash
rm -rf ~/.m2/repository
```

Puis relancez une commande Maven pour re-télécharger les dépendances.

## Commandes avancées

### Voir l'arborescence des dépendances

```bash
mvn dependency:tree
```

### Vérifier les mises à jour de dépendances

```bash
mvn versions:display-dependency-updates
```

### Profiler l'application

```bash
mvn javafx:run -DargLine="-Xprof"
```

## Support

En cas de problème:
1. Vérifiez les prérequis système
2. Consultez la section Troubleshooting
3. Vérifiez les logs dans la console
4. Consultez la [documentation Maven officielle](https://maven.apache.org/guides/)
