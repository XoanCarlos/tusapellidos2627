package com.clase;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import com.clase.modelo.Cliente;
import com.clase.persistencia.ClienteDAO;
//import com.clase.persistencia.ClienteDAOJson;
import com.clase.persistencia.ClienteDAOMySQL;
import javafx.collections.FXCollections;




public class ClientesController {
    // =========================
    // DAO (SIEMPRE ARRIBA)
    // =========================
    // Aquí JSON
    //private final ClienteDAOJson dao = new ClienteDAOJson();
    // Aquí MySQL
    private final ClienteDAO dao = new ClienteDAOMySQL();


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

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente,Integer> colId;

    @FXML
    private TableColumn<Cliente,String> colApellidos;

    @FXML
    private TableColumn<Cliente,String> colNombre;

    @FXML
    private TableColumn<Cliente,String> colMovil;

    @FXML
    private TableColumn<Cliente,String> colTipo;

    @FXML
    private TableColumn<Cliente,Boolean> colActivo;

  
    
    // ========================================
    // CONFIGURAR COLUMNAS DEL TABLEVIEW
    // ========================================
    private void configurarTabla() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMovil.setCellValueFactory(new PropertyValueFactory<>("movil"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        colActivo.setCellValueFactory(new PropertyValueFactory<>("activo"));

    }

    // ========================================
    // CARGAR CLIENTES EN LA TABLA
    // ========================================
    private void cargarTabla() {

        tablaClientes.setItems(
                FXCollections.observableArrayList(
                        dao.listar()
                )
        );
    }

    // ========================================
    // CARGAR DATOS EN EL TABLEVIEW
    // Al iniciar la aplicación, se ejecuta el método initialize() 
    // que configura las columnas de la tabla y carga los datos de los clientes desde la base de datos.
    // ========================================

    @FXML
    public void initialize() {

        configurarTabla();

        // Ajustamos el tamaño de las columnas para que se ajusten al ancho de la tabla
        tablaClientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        cargarTabla();


        // Detectar cuando el usuario selecciona una fila
        tablaClientes.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, anterior, seleccionado) -> {

                    if (seleccionado != null) {
                        cargarCliente(seleccionado.getId());
                    }

                });

    }

   
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

            // Obtener los datos del formulario
            Cliente cliente = obtenerCliente();

            // ¿Cliente nuevo o existente?
            if (txtId.getText().isBlank()) {

                // INSERT
                dao.guardar(cliente);
                System.out.println("Cliente insertado correctamente.");

            } else {

                // UPDATE
                cliente.setId(Integer.parseInt(txtId.getText()));
                dao.modificar(cliente);
                System.out.println("Cliente modificado correctamente.");

            }

            // Actualizar la tabla
            cargarTabla();

            // Limpiar el formulario
            limpiarFormulario();
        }


        // ========================================
        // CARGAR UN CLIENTE EN EL FORMULARIO
        // ========================================
        private void cargarCliente(int id) {

            Cliente cliente = dao.buscarPorId(id);

            if (cliente == null) {
                return;
            }

            txtId.setText(String.valueOf(cliente.getId()));
            txtNombre.setText(cliente.getNombre());
            txtApellidos.setText(cliente.getApellidos());
            txtDni.setText(cliente.getDni());
            txtMovil.setText(cliente.getMovil());
            txtEmail.setText(cliente.getEmail());

            switch (cliente.getTipoCliente()) {

                case "Particular" -> rbParticular.setSelected(true);

                case "Autónomo" -> rbAutonomo.setSelected(true);

                case "Empresa" -> rbEmpresa.setSelected(true);

            }

            dpFechaAlta.setValue(cliente.getFechaAlta());

            chkActivo.setSelected(cliente.isActivo());

            txtObservaciones.setText(cliente.getObservaciones());

        }

        // ========================================
        // ELIMINAR UN CLIENTE
        // ========================================
        @FXML
        private void eliminarCliente() {

            // Comprobar que existe un cliente seleccionado
            if (txtId.getText().isBlank()) {
                System.out.println("Debe seleccionar un cliente.");
                return;
            }

            // Obtener el ID del cliente
            int id = Integer.parseInt(txtId.getText());

            // Eliminar el cliente
            dao.eliminar(id);

            // Actualizar la tabla
            cargarTabla();

            // Limpiar el formulario
            limpiarFormulario();

            System.out.println("Cliente eliminado correctamente.");

        } 
    }