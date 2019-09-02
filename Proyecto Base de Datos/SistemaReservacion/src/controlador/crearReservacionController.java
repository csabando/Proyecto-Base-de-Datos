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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.cliente;
import static modelo.cliente.clien;
import static modelo.cliente.idclien;
import modelo.habitacion;
import modelo.reservacion;
import modelo.tipoHabitacion;
import modelo.tipoPago;

/**
 * FXML Controller class
 *
 * @author bryan
 */
public class crearReservacionController implements Initializable {

    
    @FXML private JFXTextArea txtdetalle;
    @FXML private DatePicker dateinicio;
    @FXML private DatePicker datefin;
    @FXML private JFXTextField txthabitacion;
    @FXML private JFXComboBox<String> cbxhabitacion;
    @FXML private JFXTextField txtpago;
    @FXML private JFXComboBox<String> cbxpago;
    
    private cliente modeloCliente = new cliente();
    private habitacion modeloHabitacion = new habitacion();
    private tipoPago modeloTipoPago = new tipoPago();
    private tipoHabitacion modeloTipoH = new tipoHabitacion();
    private ObservableList<tipoHabitacion> listtipoh = modeloTipoH.obtenerTipoH();
    private ObservableList<tipoPago> listpago = modeloTipoPago.obtenerTipoPago();
    private ObservableList<habitacion> listhabitacion = modeloHabitacion.obtenerHabitacion();
    private reservacion modeloReservacion = new reservacion();
    private ControladorValidar validar = new ControladorValidar();
    private reservacion seleccion = null;
    private ObservableList<reservacion> list = null;
    
    private long dfecha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        txthabitacion.setEditable(false);
        txtpago.setEditable(false);
        
        for(tipoHabitacion tp : listtipoh)
            cbxpago.getItems().addAll(tp.getNombre());
        for(habitacion h : listhabitacion)
            cbxhabitacion.getItems().addAll(h.getNumHabitacion());
    }

    @FXML
    private void registrar(ActionEvent event) {
        obtenerDatos();
        boolean resultado = modeloReservacion.ingresarReservacion();
        if (resultado){
            validar.mensajeIngresadoCorrecto();
        }
        else
            validar.mensajeIngresadoIncorrecto();
    }


    @FXML
    private void comboHabitacion(ActionEvent event) {
        txthabitacion.setText(cbxhabitacion.getValue());
    }

    @FXML
    private void comboPago(ActionEvent event) {
        txtpago.setText(cbxpago.getValue());
    }


    
    public void obtenerDatos(){
        modeloReservacion.setId(0);
        if(dateinicio.getValue()!=null && datefin.getValue()!= null && txtdetalle.getText()!= null&&txtpago.getText()!=null&&txthabitacion.getText()!= null ){
            Date fei = java.sql.Date.valueOf(dateinicio.getValue());
            modeloReservacion.setFechaInicio((java.sql.Date) fei);
            Date fef = java.sql.Date.valueOf(datefin.getValue());
            modeloReservacion.setFechaFin((java.sql.Date) fef);
            long startTime = fei.getTime();
            long endTime = fef.getTime();
            long diffTime = endTime - startTime;
            dfecha = TimeUnit.DAYS.convert(diffTime, TimeUnit.MILLISECONDS);
            modeloReservacion.setDetalle(txtdetalle.getText());
            for(tipoHabitacion tp : listtipoh){
                if(tp.getNombre().equals(txtpago.getText()))
                    modeloReservacion.setTotal(tp.getPrecio()*dfecha);
            }
            modeloReservacion.setHabitacion(txthabitacion.getText());
            modeloReservacion.setCliente(idclien);
            modeloReservacion.setEstado(true);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Campo");
            alert.setHeaderText(null);
            alert.setContentText("Campo vacio, revisar!");
            alert.showAndWait();
        }
            
    }
}
