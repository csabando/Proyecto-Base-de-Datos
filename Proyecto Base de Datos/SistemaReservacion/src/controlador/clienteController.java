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
import static modelo.cliente.clien;
import static modelo.empleado.admin;


public class clienteController implements Initializable {

    @FXML private Label labelAdmin;
    @FXML private Pane parent1;
    @FXML private JFXComboBox<String> cbxGestionar;
    @FXML private JFXButton btnCerrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbxGestionar.getItems().addAll("Crear","Eliminar");
        labelAdmin.setText(clien);
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
        if(seleccionG.equals("crear")){
            AnchorPane vistaGE = FXMLLoader.load(getClass().getResource(("/vista/vistaCrearReservacion.fxml")));
            parent1.getChildren().setAll(vistaGE);
        }
        else if(seleccionG.equals("eliminar")){
            AnchorPane vistaGP = FXMLLoader.load(getClass().getResource(("/vista/vistaEliminarReservacion.fxml")));
            parent1.getChildren().setAll(vistaGP);
        }
    }
    
}
