package com.university.app.controller;

import com.university.patterns.strategy.DataProcessor;
import com.university.patterns.strategy.BubbleSort;
import com.university.patterns.strategy.QuickSort;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StrategyController {
    
    public VBox createStrategyPanel() {
        VBox mainPanel = new VBox(10);
        mainPanel.setPadding(new Insets(15));

        Label titleLabel = new Label("Strategy Pattern - Stratégies de tri");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        TextArea dataArea = new TextArea();
        dataArea.setWrapText(true);
        dataArea.setPrefHeight(100);
        dataArea.setPromptText("Données à trier (séparées par des virgules)");

        TextArea resultArea = new TextArea();
        resultArea.setWrapText(true);
        resultArea.setPrefHeight(150);
        resultArea.setEditable(false);

        ComboBox<String> strategyCombo = new ComboBox<>();
        strategyCombo.getItems().addAll("Bubble Sort", "Quick Sort");
        strategyCombo.setValue("Quick Sort");

        Button generateButton = new Button("Générer données aléatoires");
        generateButton.setStyle("-fx-padding: 8;");
        generateButton.setOnAction(e -> {
            Random rand = new Random();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                sb.append(rand.nextInt(100));
                if (i < 9) sb.append(", ");
            }
            dataArea.setText(sb.toString());
        });

        Button sortButton = new Button("Trier");
        sortButton.setStyle("-fx-padding: 8;");
        sortButton.setOnAction(e -> {
            try {
                List<Integer> data = new ArrayList<>();
                String[] parts = dataArea.getText().split(",");
                for (String part : parts) {
                    data.add(Integer.parseInt(part.trim()));
                }

                DataProcessor processor = new DataProcessor();
                
                if (strategyCombo.getValue().equals("Bubble Sort")) {
                    processor.setSortingStrategy(new BubbleSort());
                } else {
                    processor.setSortingStrategy(new QuickSort());
                }

                long startTime = System.nanoTime();
                processor.process(data);
                long duration = System.nanoTime() - startTime;

                resultArea.setText("Données triées: " + data + "\n" +
                        "Stratégie: " + strategyCombo.getValue() + "\n" +
                        "Temps d'exécution: " + (duration / 1_000_000.0) + " ms");
            } catch (Exception ex) {
                resultArea.setText("Erreur: " + ex.getMessage());
            }
        });

        HBox strategyPanel = new HBox(10);
        strategyPanel.getChildren().addAll(
                new Label("Sélectionner stratégie:"), strategyCombo
        );

        HBox buttonPanel = new HBox(10);
        buttonPanel.getChildren().addAll(generateButton, sortButton);

        mainPanel.getChildren().addAll(
                titleLabel,
                new Separator(),
                new Label("Données d'entrée:"),
                dataArea,
                strategyPanel,
                buttonPanel,
                new Label("Résultat:"),
                resultArea
        );

        return mainPanel;
    }
}
