
package modelo;

import conexion.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static modelo.empleado.LOGGER;

public class tipoHabitacion {
    protected static final DBConnection CONNECTION = DBConnection.getInstance();
    protected int  idTipo;
    protected String nombre;
    protected float precio;

    public tipoHabitacion() {
    }

    public tipoHabitacion(int idTipo, String nombre, float precio) {
        this.idTipo = idTipo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public ObservableList<tipoHabitacion> obtenerTipoH(){
        ObservableList <tipoHabitacion> lista = FXCollections.observableArrayList ();
        try {
            CONNECTION.conectar();
            String consulta = "{call  obtenerTipoHabitacion ()}";
            PreparedStatement ingreso = CONNECTION.getConnection().prepareStatement(consulta);
            ResultSet resultado = ingreso.executeQuery();
            while (resultado.next()) {
                lista.add(
                        new tipoHabitacion(
                                resultado.getInt("idTipo"),
                                resultado.getString("nombre"),
                                resultado.getFloat("precio")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return lista;
    }
    
    
}
