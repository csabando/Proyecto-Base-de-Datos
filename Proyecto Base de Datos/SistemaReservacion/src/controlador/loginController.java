/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.cliente;
import modelo.empleado;
import modelo.usuario;

/**
 *
 * @author bryan
 */
public class loginController implements Initializable {

    @FXML private AnchorPane parent;
    @FXML private JFXTextField txtUsuario;
    @FXML private JFXPasswordField textClave;
    
    private ControladorValidar controladorValidar = new ControladorValidar();
    private usuario modeloUsuario = new usuario();
    private empleado modeloEmpleado = new empleado();
    private cliente modeloCliente = new cliente();
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
        String usuario = txtUsuario.getText();
        String contrasena = textClave.getText();
        if(controladorValidar.validarUsuarioContrasena(usuario, contrasena)){
            modeloUsuario.setUsuario(usuario);
            modeloUsuario.setContrasena(contrasena);
            String rol = modeloUsuario.login();
            if(rol !=null){
                asignarVistaRol(rol, event);
            }
                
        }
    }
    
    public void asignarVistaRol(String rol, ActionEvent event) throws IOException{
        if(rol.equalsIgnoreCase("empleado")){
            modeloEmpleado.obtenerUsuario(modeloUsuario);
            Parent root = FXMLLoader.load(getClass().getResource("/vista/vistaAdministrador.fxml"));
            Scene sceneEmpleado = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(sceneEmpleado);
            stage.show();
        }
        else if(rol.equalsIgnoreCase("cliente")){
            modeloCliente.obtenerUsuario(modeloUsuario);
            Parent root = FXMLLoader.load(getClass().getResource("/vista/vistaCliente.fxml"));
            Scene sceneJefe = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(sceneJefe);
            stage.show();
        }
    
    
    }
    
}
