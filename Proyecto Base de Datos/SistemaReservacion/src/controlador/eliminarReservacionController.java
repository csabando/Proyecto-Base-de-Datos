/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXComboBox;
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
public class eliminarReservacionController implements Initializable {

    @FXML private TableColumn<reservacion, String> cid;
    @FXML private TableColumn<reservacion, String> cdetalle;
    @FXML private TableColumn<reservacion, String> cfinicio;
    @FXML private TableColumn<reservacion, String> cffin;
    @FXML private TableColumn<reservacion, String> ctotal;
    @FXML private TableColumn<reservacion, String> chabitacion;
    @FXML private TableColumn<reservacion, String> cestado;
    @FXML private TableView<reservacion> tableR;
    

    private reservacion modeloReservacion = new reservacion();
    private ControladorValidar validar = new ControladorValidar();
    private reservacion seleccion = null;
    private ObservableList<reservacion> list = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cid.setCellValueFactory(new PropertyValueFactory<>("id"));
        cdetalle.setCellValueFactory(new PropertyValueFactory<>("detalle"));
        cfinicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        cffin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        ctotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        chabitacion.setCellValueFactory(new PropertyValueFactory<>("habitacion"));
        cestado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        llenarTable();
    }
    public void llenarTable(){
        if(list == null){
            list = modeloReservacion.llenarTableReservacionCliente();
            tableR.setItems(list);
        }
        else{
            list.removeAll(list);
            list = modeloReservacion.llenarTableReservacionCliente();
            tableR.setItems(list);
        }
        
    }

    @FXML
    private void eliminar(ActionEvent event) {
        boolean resultado = modeloReservacion.eliminarReservacion();
        if (resultado){
            validar.mensajeEliminadoCorrecto();
            llenarTable();
        }
        else
            validar.mensajeEliminadoIncorrecto();
    }

    @FXML
    private void seleccionarReservacion(MouseEvent event) {
        seleccion = (reservacion)tableR.getSelectionModel().getSelectedItem();
        modeloReservacion.setId(seleccion.getId());
    }
    
}
