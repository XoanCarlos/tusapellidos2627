package com.clase;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;




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

    @FXML
    private void salirAplicacion() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.initOwner(rootPane.getScene().getWindow());
        alerta.initModality(Modality.APPLICATION_MODAL);

        alerta.setTitle("Exit CRM");
        alerta.setHeaderText("Close CRM");
        alerta.setContentText("Are you sure you want to exit the CRM?");

        Optional<ButtonType> resultado = alerta.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    @FXML
    private void informeClientes() {
        System.out.println("Informe clientes");
    }

    @FXML
    private void informeProductos() {
        System.out.println("Informe productos");
    }

    @FXML
    private void imprimirInforme() {
        System.out.println("Imprimir factura");
    }

    @FXML
    private void acercaDe() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.initOwner(rootPane.getScene().getWindow());
        alerta.initModality(Modality.APPLICATION_MODAL);

        alerta.setTitle("About");
        alerta.setHeaderText("CRM JavaFX");

        alerta.setContentText("""
                CRM v0.0.1
                
                Application developed in JavaFX for managing clients and products.
                
                Devoloper:
                Juan Carlos
                
                Curso:
                Interfaces Development 202X
                """);

        alerta.showAndWait();
    }
    
    @FXML
    private void abrirExplorador() {

        try {
            Desktop.getDesktop().open(new File(System.getProperty("user.home")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}