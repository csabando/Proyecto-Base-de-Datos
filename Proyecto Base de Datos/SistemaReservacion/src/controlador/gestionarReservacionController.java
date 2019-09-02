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
import modelo.habitacion;
import modelo.reservacion;
import modelo.tipoHabitacion;
import modelo.tipoPago;

/**
 * FXML Controller class
 *
 * @author bryan
 */
public class gestionarReservacionController implements Initializable {

    @FXML private TableColumn<reservacion, String> cid;
    @FXML private TableColumn<reservacion, String> cdetalle;
    @FXML private TableColumn<reservacion, String> cfinicio;
    @FXML private TableColumn<reservacion, String> cffin;
    @FXML private TableColumn<reservacion, String> ctotal;
    @FXML private TableColumn<reservacion, String> ccliente;
    @FXML private TableColumn<reservacion, String> chabitacion;
    @FXML private TableColumn<reservacion, String> cestado;
    @FXML private JFXTextField txtid;
    @FXML private JFXTextField txtdetalle;
    @FXML private DatePicker dateinicio;
    @FXML private DatePicker datefin;
    @FXML private JFXTextField txttotal;
    @FXML private JFXTextField txtcliente;
    @FXML private JFXComboBox<String> cbxcliente;
    @FXML private JFXTextField txthabitacion;
    @FXML private JFXComboBox<String> cbxhabitacion;
    @FXML private JFXTextField txtpago;
    @FXML private JFXComboBox<String> cbxpago;
    @FXML private JFXTextField txtestado;
    @FXML private JFXComboBox<String> cbxestado;
    @FXML private TableView<reservacion> tableR;
    
    private cliente modeloCliente = new cliente();
    private habitacion modeloHabitacion = new habitacion();
    private reservacion modeloReservacion = new reservacion();
    private tipoPago modeloTipoPago = new tipoPago();
    private tipoHabitacion modeloTipoH = new tipoHabitacion();
    private ObservableList<tipoHabitacion> listtipoh = modeloTipoH.obtenerTipoH();
    private ObservableList<tipoPago> listpago = modeloTipoPago.obtenerTipoPago();
    private ObservableList<habitacion> listhabitacion = modeloHabitacion.obtenerHabitacion();
    private ObservableList<cliente> listcliente = modeloCliente.obtenerCliente();
    private ControladorValidar validar = new ControladorValidar();
    private reservacion seleccion = null;
    private ObservableList<reservacion> list = null;
    
    private long dfecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtid.setEditable(false);
        txttotal.setEditable(false);
        txtcliente.setEditable(false);
        txthabitacion.setEditable(false);
        txtpago.setEditable(false);
        txtestado.setEditable(false);
        cbxestado.getItems().addAll("true","false");
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cdetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        cfinicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        cffin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        ctotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        ccliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        chabitacion.setCellValueFactory(new PropertyValueFactory<>("habitacion"));
        cestado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        for(tipoHabitacion tp : listtipoh)
            cbxpago.getItems().addAll(tp.getNombre());
        for(cliente c : listcliente)
            cbxcliente.getItems().addAll(c.getIdCliente());
        for(habitacion h : listhabitacion)
            cbxhabitacion.getItems().addAll(h.getNumHabitacion());
        llenarTable();
    }
    public void llenarTable(){
        if(list == null){
            list = modeloReservacion.llenarTableReservacion();
            tableR.setItems(list);
        }
        else{
            list.removeAll(list);
            list = modeloReservacion.llenarTableReservacion();
            tableR.setItems(list);
        }
        
    }
    @FXML
    private void actualizar(ActionEvent event) {
        obtenerDatos();
        modeloReservacion.setId(Integer.parseInt(txtid.getText()));
        boolean resultado = modeloReservacion.actualizarReservacion();
        if (resultado){
            validar.mensajeActualizadoCorrecto();
            llenarTable();
        }
        else
            validar.mensajeActualizadoIncorrecto();
    }
    @FXML
    private void eliminar(ActionEvent event) {
        obtenerDatos();
        modeloReservacion.setId(Integer.parseInt(txtid.getText()));
        boolean resultado = modeloReservacion.eliminarReservacion();
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
        boolean resultado = modeloReservacion.ingresarReservacion();
        if (resultado){
            validar.mensajeIngresadoCorrecto();
            llenarTable();
        }
        else
            validar.mensajeIngresadoIncorrecto();
    }
    @FXML
    private void comboCliente(ActionEvent event) {
        txtcliente.setText(cbxcliente.getValue());
    }

    @FXML
    private void comboHabitacion(ActionEvent event) {
        txthabitacion.setText(cbxhabitacion.getValue());
    }

    @FXML
    private void comboPago(ActionEvent event) {
        txtpago.setText(cbxpago.getValue());
    }
    @FXML
    private void comboEstado(ActionEvent event) {
        txtestado.setText(cbxestado.getValue());
    }
    @FXML
    private void seleccionarReservacion(MouseEvent event) {
        seleccion = (reservacion)tableR.getSelectionModel().getSelectedItem();
        txtid.setText(String.valueOf(seleccion.getId()));
        txtdetalle.setText(seleccion.getDetalle());
        txttotal.setText(String.valueOf(seleccion.getTotal()));
        txtcliente.setText(seleccion.getCliente());
        txthabitacion.setText(seleccion.getHabitacion());
        txtestado.setText(String.valueOf(seleccion.isEstado()));
        Date fei = java.sql.Date.valueOf(String.valueOf(seleccion.getFechaInicio()));
        modeloReservacion.setFechaInicio((java.sql.Date) fei);
        Date fef = java.sql.Date.valueOf(String.valueOf(seleccion.getFechaFin()));
        modeloReservacion.setFechaFin((java.sql.Date) fef);
        long startTime = fei.getTime();
        long endTime = fef.getTime();
        long diffTime = endTime - startTime;
        dfecha = TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
        modeloReservacion.setTotal(seleccion.getTotal());
    }
    
    public void obtenerDatos(){
        if(txtid.getText()==null)
            modeloReservacion.setId(Integer.parseInt(txtid.getText()));
        if(dateinicio.getValue()!=null && datefin.getValue()!= null){
            Date fei = java.sql.Date.valueOf(dateinicio.getValue());
            modeloReservacion.setFechaInicio((java.sql.Date) fei);
            Date fef = java.sql.Date.valueOf(datefin.getValue());
            modeloReservacion.setFechaFin((java.sql.Date) fef);
            long startTime = fei.getTime();
            long endTime = fef.getTime();
            long diffTime = endTime - startTime;
            dfecha = TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
        }
        modeloReservacion.setDetalle(txtdetalle.getText());
        for(tipoHabitacion tp : listtipoh){
             if(tp.getNombre().equals(txtpago.getText()))
                modeloReservacion.setTotal(tp.getPrecio()*dfecha);
        }
        modeloReservacion.setCliente(txtcliente.getText());
        modeloReservacion.setEstado(Boolean.parseBoolean(txtestado.getText()));
        modeloReservacion.setHabitacion(txthabitacion.getText());
            
    }
}
