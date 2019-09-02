/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.cliente;
import modelo.empleado;
import modelo.habitacion;
import modelo.reservacion;
import modelo.tipoHabitacion;
import modelo.tipoPago;

/**
 * FXML Controller class
 *
 * @author bryan
 */
public class gestionarClienteController implements Initializable {

    @FXML private TableColumn<cliente, String> cid;
    @FXML private JFXTextField txtid;
    
    private cliente modeloCliente = new cliente();
    private ControladorValidar validar = new ControladorValidar();
    private ObservableList<cliente> list = null;
    @FXML private TableView<cliente> tableE;
    @FXML private TableColumn<cliente, String> cnom;
    @FXML private TableColumn<cliente, String> cape;
    @FXML private TableColumn<cliente, String> cdir;
    @FXML private TableColumn<cliente, String> ctele;
    @FXML private TableColumn<cliente, String> cusu;;
    @FXML private TableColumn<cliente, String> cclave;
    @FXML private JFXTextField txtnombre;
    @FXML private JFXTextField txtapellido;
    @FXML private JFXTextField txtdir;
    @FXML private JFXTextField txttele;
    @FXML private JFXTextField txtuser;
    @FXML private JFXTextField txtclave;
    private cliente seleccion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cid.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cape.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        cdir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        ctele.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        cusu.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        cclave.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
        llenarTable();
    }
    public void llenarTable(){
        if(list == null){
            list = modeloCliente.obtenerCliente();
            tableE.setItems(list);
        }
        else{
            list.removeAll(list);
            list = modeloCliente.obtenerCliente();
            tableE.setItems(list);
        }
        
    }
    @FXML
    private void actualizar(ActionEvent event) {
        obtenerDatos();
        modeloCliente.setIdCliente(txtid.getText());
        boolean resultado = modeloCliente.actualizarCliente();
        if (resultado){
            validar.mensajeActualizadoCorrecto();
            llenarTable();
        }
        else
            validar.mensajeActualizadoIncorrecto();
    }

    @FXML
    private void registrar(ActionEvent event) {
        obtenerDatos();
        boolean resultado = modeloCliente.ingresarCliente();
        if (resultado){
            validar.mensajeIngresadoCorrecto();
            llenarTable();
        }
        else
            validar.mensajeIngresadoIncorrecto();
    }

    @FXML
    private void seleccionarEmpleado(MouseEvent event) {
        seleccion = (cliente)tableE.getSelectionModel().getSelectedItem();
        txtid.setText(seleccion.getIdCliente());
        txtnombre.setText(seleccion.getNombre());
        txtapellido.setText(seleccion.getApellido());
        txtdir.setText(seleccion.getDireccion());
        txttele.setText(seleccion.getTelefono());
        txtuser.setText(seleccion.getUsuario());
        txtclave.setText(seleccion.getContrasena());
        
    }
    
    public void obtenerDatos(){
        modeloCliente.setIdCliente(txtid.getText());
        modeloCliente.setNombre(txtnombre.getText());
        modeloCliente.setApellido(txtapellido.getText());
        modeloCliente.setDireccion(txtdir.getText());
        modeloCliente.setTelefono(txttele.getText());
        modeloCliente.setUsuario(txtuser.getText());
        modeloCliente.setContrasena(txtclave.getText());
            
    }

    
}
