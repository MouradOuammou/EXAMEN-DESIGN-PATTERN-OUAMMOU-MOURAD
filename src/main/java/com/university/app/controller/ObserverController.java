package com.university.app.controller;

import com.university.patterns.observer.EventManager;
import com.university.patterns.observer.LoggingListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class ObserverController {
    
    public VBox createObserverPanel() {
        VBox mainPanel = new VBox(10);
        mainPanel.setPadding(new Insets(15));

        Label titleLabel = new Label("Observer Pattern - Gestion des événements");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        EventManager eventManager = new EventManager("UserAction");
        LoggingListener listener = new LoggingListener();

        TextArea eventLogArea = new TextArea();
        eventLogArea.setWrapText(true);
        eventLogArea.setPrefHeight(300);
        eventLogArea.setEditable(false);

        // Capture de la sortie console pour l'afficher dans l'UI
        TextAreaAppender appender = new TextAreaAppender(eventLogArea);
        java.util.logging.Logger.getLogger("").addHandler(appender);

        TextField eventDataField = new TextField();
        eventDataField.setPromptText("Entrez les données de l'événement");
        eventDataField.setPrefWidth(300);

        Button subscribeButton = new Button("S'abonner");
        subscribeButton.setStyle("-fx-padding: 8;");
        subscribeButton.setOnAction(e -> {
            eventManager.subscribe(listener);
            eventLogArea.appendText("Listener abonné à l'événement\n");
        });

        Button fireEventButton = new Button("Déclencher l'événement");
        fireEventButton.setStyle("-fx-padding: 8;");
        fireEventButton.setOnAction(e -> {
            EventManager.Event event = new EventManager.Event(eventDataField.getText());
            eventLogArea.appendText(">>> Événement déclenché: " + eventDataField.getText() + "\n");
            eventManager.notify(event);
            eventDataField.clear();
        });

        Button unsubscribeButton = new Button("Se désabonner");
        unsubscribeButton.setStyle("-fx-padding: 8;");
        unsubscribeButton.setOnAction(e -> {
            eventManager.unsubscribe(listener);
            eventLogArea.appendText("Listener désabonné\n");
        });

        HBox buttonPanel = new HBox(10);
        buttonPanel.getChildren().addAll(subscribeButton, fireEventButton, unsubscribeButton);

        mainPanel.getChildren().addAll(
                titleLabel,
                new Separator(),
                new Label("Données de l'événement:"),
                eventDataField,
                buttonPanel,
                new Label("Journal des événements:"),
                eventLogArea
        );

        return mainPanel;
    }

    // Simple class to redirect System.out to TextArea
    private static class TextAreaAppender extends java.util.logging.Handler {
        private final TextArea textArea;

        public TextAreaAppender(TextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void publish(java.util.logging.LogRecord record) {
            textArea.appendText(record.getMessage() + "\n");
        }

        @Override
        public void flush() {}

        @Override
        public void close() throws SecurityException {}
    }
}
