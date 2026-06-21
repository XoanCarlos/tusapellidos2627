package com.clase;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(getClass().getResource("/com/clase/mainwindow.fxml"));

                        
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(
            getClass()
                .getResource("/com/clase/css/styles.css")
                .toExternalForm()
        );

        stage.setTitle("Sistema de Gestión Empresarial");
        stage.setMaximized(true);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}