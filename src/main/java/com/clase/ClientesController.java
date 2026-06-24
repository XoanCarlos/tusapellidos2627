package com.clase;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;


public class ClientesController {

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
}