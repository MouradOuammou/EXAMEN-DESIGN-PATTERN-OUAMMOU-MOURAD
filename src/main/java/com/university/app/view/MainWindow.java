package com.university.app.view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import com.university.app.controller.BuilderController;
import com.university.app.controller.ObserverController;
import com.university.app.controller.StrategyController;
import com.university.app.controller.AdapterController;

public class MainWindow {
    public void show(Stage primaryStage) {
        primaryStage.setTitle("Design Patterns Project - Démonstration Interactive");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        BuilderController builderController = new BuilderController();
        ObserverController observerController = new ObserverController();
        StrategyController strategyController = new StrategyController();
        AdapterController adapterController = new AdapterController();

        Tab builderTab = new Tab("Builder", builderController.createBuilderPanel());
        builderTab.setClosable(false);

        Tab observerTab = new Tab("Observer", observerController.createObserverPanel());
        observerTab.setClosable(false);

        Tab strategyTab = new Tab("Strategy", strategyController.createStrategyPanel());
        strategyTab.setClosable(false);

        Tab adapterTab = new Tab("Adapter", adapterController.createAdapterPanel());
        adapterTab.setClosable(false);

        tabPane.getTabs().addAll(builderTab, observerTab, strategyTab, adapterTab);

        Scene scene = new Scene(tabPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}