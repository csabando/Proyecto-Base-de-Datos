/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static modelo.empleado.admin;


public class AdministradorController implements Initializable {

    @FXML private Label labelAdmin;
    @FXML private Pane parent1;
    @FXML private JFXComboBox<String> cbxGestionar;
    @FXML private JFXButton btnCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxGestionar.getItems().addAll("Cliente","Empleado","Reservaciones");
        labelAdmin.setText(admin);
    }    

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cambiarGestionar(ActionEvent event) throws IOException {
        String seleccion = cbxGestionar.getValue();
        gestionarSeleccion(seleccion);
    }
    
    public void gestionarSeleccion(String seleccion) throws IOException{
        String seleccionG = seleccion.toLowerCase();
        if(seleccionG.equals("cliente")){
            AnchorPane vistaGE = FXMLLoader.load(getClass().getResource(("/vista/vistaGestionarCliente.fxml")));
            parent1.getChildren().setAll(vistaGE);
        }
        else if(seleccionG.equals("empleado")){
            AnchorPane vistaGP = FXMLLoader.load(getClass().getResource(("/vista/vistaGestionarEmpleado.fxml")));
            parent1.getChildren().setAll(vistaGP);
        }
        else if(seleccionG.equals("reservaciones")){
            AnchorPane vistaGI = FXMLLoader.load(getClass().getResource(("/vista/vistaGestionarReservacion.fxml")));
            parent1.getChildren().setAll(vistaGI);
        }
    }
    
}
