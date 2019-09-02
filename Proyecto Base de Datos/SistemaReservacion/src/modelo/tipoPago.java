
package modelo;

import conexion.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static modelo.empleado.LOGGER;
import static modelo.usuario.CONNECTION;

public class tipoPago {
    protected static final DBConnection CONNECTION = DBConnection.getInstance();
    protected int id;
    protected String tipo;
    protected String descripcion;

    public tipoPago() {
    }

    public tipoPago(int id, String tipo, String descripcion) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public ObservableList<tipoPago> obtenerTipoPago(){
        ObservableList <tipoPago> lista = FXCollections.observableArrayList ();
        try {
            CONNECTION.conectar();
            String consulta = "{call  obtenerTipoPago ()}";
            PreparedStatement ingreso = CONNECTION.getConnection().prepareStatement(consulta);
            ResultSet resultado = ingreso.executeQuery();
            while (resultado.next()) {
                lista.add(
                        new tipoPago(
                                resultado.getInt("idTipo"),
                                resultado.getString("tipo"),
                                resultado.getString("Descripcion")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return lista;
    }
}
