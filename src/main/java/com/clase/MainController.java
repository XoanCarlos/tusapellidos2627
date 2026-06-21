package com.clase;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

public class MainController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private void mostrarClientes() {
        cargarVista("/com/clase/clientes.fxml");
    }

    @FXML
    private void mostrarProductos() {
        cargarVista("/com/clase/productos.fxml");
    }

    private void cargarVista(String fxml) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource(fxml));

            rootPane.setCenter(loader.load());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}