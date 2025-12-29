package com.university.app;

import javafx.application.Application;
import javafx.stage.Stage;
import com.university.app.view.MainWindow;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        AppContext appContext = AppContext.getInstance();
        
        MainWindow mainWindow = new MainWindow();
        mainWindow.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
