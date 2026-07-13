    package com.clase;
    import java.awt.Desktop;
    import java.io.File;
    import java.io.IOException;
    import java.util.Optional;

    import com.clase.informes.InformeClientes;

    import javafx.application.Platform;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.scene.control.Alert;
    import javafx.scene.control.ButtonType;
    import javafx.scene.layout.BorderPane;
    import javafx.stage.Modality;
    import javafx.scene.control.Label;
    import javafx.scene.Parent;


    public class MainController {

        @FXML
        private BorderPane rootPane;
        private Object controladorActivo;


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

                Parent vista = loader.load();

                // Guardar el controlador activo    
                controladorActivo = loader.getController();
                
                // Establecer el MainController en el controlador activo
                if (controladorActivo instanceof ClientesController) {
                    ((ClientesController) controladorActivo).setMainController(this);
                } else if (controladorActivo instanceof ProductosController) {
                    System.out.println("ProductosController activo");
                    //((ProductosController) controladorActivo).setMainController(this);
                }
                
                rootPane.setCenter(vista);

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
        private void imprimirInforme() {

            if (controladorActivo instanceof ClientesController) {

                InformeClientes.mostrar();

            } else if (controladorActivo instanceof ProductosController) {

                System.out.println("Imprimir informe de productos");

            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Imprimir");
                alert.setHeaderText(null);
                alert.setContentText("No existe ningún informe para esta pantalla.");
                alert.showAndWait();

            }
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

        @FXML
            private Label lblEstado;

            public void mostrarEstado(String mensaje) {
                lblEstado.setText(mensaje);
            }

    }