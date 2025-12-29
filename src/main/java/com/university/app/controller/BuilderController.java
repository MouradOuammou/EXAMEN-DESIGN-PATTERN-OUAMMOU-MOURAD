package com.university.app.controller;

import com.university.patterns.builder.DocumentBuilder;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class BuilderController {
    
    public VBox createBuilderPanel() {
        VBox mainPanel = new VBox(10);
        mainPanel.setPadding(new Insets(15));

        Label titleLabel = new Label("Builder Pattern - Construction de documents");
        titleLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        TextField titleField = new TextField();
        titleField.setPromptText("Titre du document");
        titleField.setPrefWidth(300);

        TextField authorField = new TextField();
        authorField.setPromptText("Auteur");

        Spinner<Integer> fontSizeSpinner = new Spinner<>(8, 24, 12);
        fontSizeSpinner.setStyle("-fx-padding: 5;");

        CheckBox headerCheckBox = new CheckBox("Ajouter un en-tête");
        CheckBox footerCheckBox = new CheckBox("Ajouter un pied de page");

        TextArea resultArea = new TextArea();
        resultArea.setWrapText(true);
        resultArea.setPrefHeight(200);
        resultArea.setEditable(false);

        Button buildButton = new Button("Construire le document");
        buildButton.setStyle("-fx-padding: 8; -fx-font-size: 12;");
        buildButton.setOnAction(e -> {
            DocumentBuilder.Document doc = new DocumentBuilder()
                    .withTitle(titleField.getText())
                    .withAuthor(authorField.getText())
                    .withFontSize(fontSizeSpinner.getValue())
                    .build();
            
            if (headerCheckBox.isSelected()) {
                doc = new DocumentBuilder()
                        .withTitle(titleField.getText())
                        .withAuthor(authorField.getText())
                        .withFontSize(fontSizeSpinner.getValue())
                        .addHeader()
                        .build();
            }
            
            if (footerCheckBox.isSelected()) {
                doc = new DocumentBuilder()
                        .withTitle(titleField.getText())
                        .withAuthor(authorField.getText())
                        .withFontSize(fontSizeSpinner.getValue())
                        .addHeader()
                        .addFooter()
                        .build();
            }
            
            resultArea.setText(doc.toString());
        });

        HBox inputPanel = new HBox(10);
        inputPanel.getChildren().addAll(
                new Label("Titre:"), titleField,
                new Label("Auteur:"), authorField
        );

        HBox optionsPanel = new HBox(10);
        optionsPanel.getChildren().addAll(
                new Label("Taille police:"), fontSizeSpinner,
                headerCheckBox, footerCheckBox
        );

        mainPanel.getChildren().addAll(
                titleLabel,
                new Separator(),
                inputPanel,
                optionsPanel,
                buildButton,
                new Label("Résultat:"),
                resultArea
        );

        return mainPanel;
    }
}
