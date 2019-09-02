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
public class gestionarEmpleadoController implements Initializable {

    @FXML private TableColumn<empleado, String> cid;
    @FXML private TableColumn<empleado, String> cestado;
    @FXML private JFXTextField txtid;
    @FXML private JFXTextField txtestado;
    @FXML private JFXComboBox<String> cbxestado;
    
    private empleado modeloEmpleado = new empleado();
    private ControladorValidar validar = new ControladorValidar();
    private ObservableList<empleado> list = null;
    @FXML private TableView<empleado> tableE;
    @FXML private TableColumn<empleado, String> cnom;
    @FXML private TableColumn<empleado, String> cape;
    @FXML private TableColumn<empleado, String> cdir;
    @FXML private TableColumn<empleado, String> ctele;
    @FXML private TableColumn<empleado, String> csal;
    @FXML private TableColumn<empleado, String> cturn;
    @FXML private TableColumn<empleado, String> ctipo;
    @FXML private TableColumn<empleado, String> cusu;;
    @FXML private TableColumn<empleado, String> cclave;
    @FXML private JFXTextField txtnombre;
    @FXML private JFXTextField txtapellido;
    @FXML private JFXTextField txtdir;
    @FXML private JFXTextField txttele;
    @FXML private JFXTextField txtsal;
    @FXML private JFXTextField txtturn;
    @FXML private JFXComboBox<String> cbxturno;
    @FXML private JFXTextField txttipo;
    @FXML private JFXTextField txtuser;
    @FXML private JFXTextField txtclave;
    private empleado seleccion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtestado.setEditable(false);
        cbxestado.getItems().addAll("true","false");
        cbxturno.getItems().addAll("T","M");
        cid.setCellValueFactory(new PropertyValueFactory<>("idEmpleado"));
        cnom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        cape.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        cdir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        ctele.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        csal.setCellValueFactory(new PropertyValueFactory<>("salario"));
        cturn.setCellValueFactory(new PropertyValueFactory<>("turno"));
        ctipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        cusu.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        cclave.setCellValueFactory(new PropertyValueFactory<>("contrasena"));
        cestado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        llenarTable();
    }
    public void llenarTable(){
        if(list == null){
            list = modeloEmpleado.llenarTableEmpleado();
            tableE.setItems(list);
        }
        else{
            list.removeAll(list);
            list = modeloEmpleado.llenarTableEmpleado();
            tableE.setItems(list);
        }
        
    }
    @FXML
    private void actualizar(ActionEvent event) {
        obtenerDatos();
        modeloEmpleado.setIdEmpleado(txtid.getText());
        boolean resultado = modeloEmpleado.actualizarEmpleado();
        if (resultado){
            validar.mensajeActualizadoCorrecto();
            llenarTable();
        }
        else
            validar.mensajeActualizadoIncorrecto();
    }
    @FXML
    private void eliminar(ActionEvent event) {
        modeloEmpleado.setIdEmpleado(txtid.getText());
        boolean resultado = modeloEmpleado.eliminarEmpleado();
        if (resultado){
            validar.mensajeEliminadoCorrecto();
            llenarTable();
        }
        else
            validar.mensajeEliminadoIncorrecto();
    }

    @FXML
    private void registrar(ActionEvent event) {
        obtenerDatos();
        boolean resultado = modeloEmpleado.ingresarEmpleado();
        if (resultado){
            validar.mensajeIngresadoCorrecto();
            llenarTable();
        }
        else
            validar.mensajeIngresadoIncorrecto();
    }

    @FXML
    private void comboPago(ActionEvent event) {
        txtturn.setText(cbxturno.getValue());
    }
    @FXML
    private void comboEstado(ActionEvent event) {
        txtestado.setText(cbxestado.getValue());
    }
    @FXML
    private void seleccionarEmpleado(MouseEvent event) {
        seleccion = (empleado)tableE.getSelectionModel().getSelectedItem();
        txtid.setText(seleccion.getIdEmpleado());
        txtnombre.setText(seleccion.getNombre());
        txtapellido.setText(seleccion.getApellido());
        txtdir.setText(seleccion.getDireccion());
        txttele.setText(seleccion.getTelefono());
        txtsal.setText(String.valueOf(seleccion.getSalario()));
        txtturn.setText(seleccion.getTurno());
        txttipo.setText(seleccion.getTipo());
        txtuser.setText(seleccion.getUsuario());
        txtclave.setText(seleccion.getContrasena());
        txtestado.setText(String.valueOf(seleccion.isEstado()));
        
    }
    
    public void obtenerDatos(){
        modeloEmpleado.setIdEmpleado(txtid.getText());
        modeloEmpleado.setNombre(txtnombre.getText());
        modeloEmpleado.setApellido(txtapellido.getText());
        modeloEmpleado.setDireccion(txtdir.getText());
        modeloEmpleado.setTelefono(txttele.getText());
        modeloEmpleado.setSalario(Float.parseFloat(txtsal.getText()));
        modeloEmpleado.setTurno(txtturn.getText());
        modeloEmpleado.setTipo(txttipo.getText());
        modeloEmpleado.setUsuario(txtuser.getText());
        modeloEmpleado.setContrasena(txtclave.getText());
        modeloEmpleado.setEstado(Boolean.parseBoolean(txtestado.getText()));
            
    }

    
}
