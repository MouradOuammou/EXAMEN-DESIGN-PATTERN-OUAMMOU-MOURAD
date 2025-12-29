package com.university.app.controller;

import com.university.patterns.adapter.DataSourceAdapter;
import com.university.patterns.adapter.LegacyDataSource;
import com.university.patterns.adapter.ModernDataInterface;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;

public class AdapterController {
    
    public VBox createAdapterPanel() {
        VBox mainPanel = new VBox(10);
        mainPanel.setPadding(new Insets(15));

        Label titleLabel = new Label("Adapter Pattern - Adaptation d'interfaces");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        TextArea legacyOutputArea = new TextArea();
        legacyOutputArea.setWrapText(true);
        legacyOutputArea.setPrefHeight(100);
        legacyOutputArea.setEditable(false);

        TextArea modernOutputArea = new TextArea();
        modernOutputArea.setWrapText(true);
        modernOutputArea.setPrefHeight(100);
        modernOutputArea.setEditable(false);

        ListView<String> modernListView = new ListView<>();
        modernListView.setPrefHeight(150);

        Button retrieveLegacyButton = new Button("Récupérer données (Legacy)");
        retrieveLegacyButton.setStyle("-fx-padding: 8;");
        retrieveLegacyButton.setOnAction(e -> {
            LegacyDataSource legacy = new LegacyDataSource();
            String[] data = legacy.retrieveData();
            legacyOutputArea.setText("Données Legacy (tableau):\n" + 
                    java.util.Arrays.toString(data));
        });

        Button adaptDataButton = new Button("Adapter et afficher");
        adaptDataButton.setStyle("-fx-padding: 8;");
        adaptDataButton.setOnAction(e -> {
            LegacyDataSource legacy = new LegacyDataSource();
            ModernDataInterface adapter = new DataSourceAdapter(legacy);
            
            modernOutputArea.setText("Données adaptées (List):\n" + 
                    adapter.getData());
            
            modernListView.setItems(FXCollections.observableArrayList(adapter.getData()));
        });

        HBox buttonPanel = new HBox(10);
        buttonPanel.getChildren().addAll(retrieveLegacyButton, adaptDataButton);

        mainPanel.getChildren().addAll(
                titleLabel,
                new Separator(),
                new Label("Approche Legacy (tableau):"),
                legacyOutputArea,
                new Label("Approche adaptée (List):"),
                modernOutputArea,
                new Label("Vue ListView:"),
                modernListView,
                buttonPanel
        );

        return mainPanel;
    }
}
