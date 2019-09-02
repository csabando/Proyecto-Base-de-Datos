
package modelo;

import conexion.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static modelo.empleado.LOGGER;

public class habitacion {
    protected static final DBConnection CONNECTION = DBConnection.getInstance();
    protected int idHabitacion;
    protected String numHabitacion;
    protected String descripcion;
    protected int numCama;
    protected boolean estado;

    public habitacion() {
    }

    public habitacion(int idHabitacion, String numHabitacion, String descripcion, int numCama, boolean estado) {
        this.idHabitacion = idHabitacion;
        this.numHabitacion = numHabitacion;
        this.descripcion = descripcion;
        this.numCama = numCama;
        this.estado = estado;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(String numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumCama() {
        return numCama;
    }

    public void setNumCama(int numCama) {
        this.numCama = numCama;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
    public ObservableList<habitacion> obtenerHabitacion(){
        ObservableList <habitacion> lista = FXCollections.observableArrayList ();
        try {
            CONNECTION.conectar();
            String consulta = "{call  obtenerHabitacion ()}";
            PreparedStatement ingreso = CONNECTION.getConnection().prepareStatement(consulta);
            ResultSet resultado = ingreso.executeQuery();
            while (resultado.next()) {
                lista.add(
                        new habitacion(
                                resultado.getInt("idHabitacion"),
                                resultado.getString("numeroHabitacion"),
                                resultado.getString("descripcion"),
                                resultado.getInt("numeroDeCama"),
                                resultado.getBoolean("estado")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return lista;
    }
}
