package com.clase;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import com.clase.modelo.Cliente;
import com.clase.persistencia.ClienteDAOJson;

public class ClientesController {
    // =========================
    // DAO (SIEMPRE ARRIBA)
    // =========================
    private final ClienteDAOJson dao = new ClienteDAOJson();


    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidos;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtMovil;

    @FXML
    private TextField txtEmail;

    @FXML
    private RadioButton rbParticular;

    @FXML
    private RadioButton rbAutonomo;

    @FXML
    private RadioButton rbEmpresa;

    @FXML
    private DatePicker dpFechaAlta;

    @FXML
    private CheckBox chkActivo;

    @FXML
    private TextArea txtObservaciones;

    @FXML
    private Button btnNuevo;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnEliminar;

    @FXML
    private ToggleGroup grupoTipoCliente;


    // =========================================
    // CONVERTIR FORMULARIO -> OBJETO CLIENTE
    // =========================================
    private Cliente obtenerCliente() {

        Cliente cliente = new Cliente();

        // Campos básicos
        cliente.setNombre(txtNombre.getText());
        cliente.setApellidos(txtApellidos.getText());
        cliente.setDni(txtDni.getText());
        cliente.setMovil(txtMovil.getText());
        cliente.setEmail(txtEmail.getText());

        // Tipo cliente (más profesional con ToggleGroup)
        if (grupoTipoCliente.getSelectedToggle() != null) {
            cliente.setTipoCliente(
                ((RadioButton) grupoTipoCliente.getSelectedToggle()).getText()
            );
        }

        // Fecha (evitar null)
        if (dpFechaAlta.getValue() != null) {
            cliente.setFechaAlta(dpFechaAlta.getValue());
        }

        // Estado activo
        cliente.setActivo(chkActivo.isSelected());

        // Observaciones
        cliente.setObservaciones(txtObservaciones.getText());

        return cliente;
       }
        @FXML
        private void nuevoCliente() {
            limpiarFormulario();
        }

        private void limpiarFormulario() {

            txtId.clear();
            txtNombre.clear();
            txtApellidos.clear();
            txtDni.clear();
            txtMovil.clear();
            txtEmail.clear();

            grupoTipoCliente.selectToggle(null);

            dpFechaAlta.setValue(null);

            chkActivo.setSelected(false);

            txtObservaciones.clear();
           }
       
           @FXML
   
        private void guardarCliente() {

            Cliente cliente = obtenerCliente();

            dao.guardar(cliente);

            System.out.println("GUARDADO EN JSON ✔");
        }
    }