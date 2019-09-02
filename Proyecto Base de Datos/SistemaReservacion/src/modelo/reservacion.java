
package modelo;

import conexion.DBConnection;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static modelo.cliente.idclien;
import static modelo.empleado.LOGGER;


public class reservacion {
    protected static final DBConnection CONNECTION = DBConnection.getInstance();
    private final String actualizar = "{call  actualizarReservacion (?,?,?,?,?,?,?,?)}";
    private final String ingresar = "{call   ingresarReservacion (?,?,?,?,?,?,?,?)}";
    private final String eliminar = "{call   eliminarReservacion (?)}";
    protected int id;
    protected Date fechaInicio;
    protected Date fechaFin;
    protected String detalle;
    protected float total;
    protected boolean estado;
    protected String cliente;
    protected String habitacion;

    public reservacion(int id, Date fechaInicio, Date fechaFin, String detalle, float total, boolean estado, String cliente, String habitacion) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.detalle = detalle;
        this.total = total;
        this.estado = estado;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    public reservacion() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    @Override
    public String toString() {
        return "reservacion{" + "id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", detalle=" + detalle + ", total=" + total + ", estado=" + estado + ", cliente=" + cliente + ", habitacion=" + habitacion + '}';
    }
    
    public ObservableList<reservacion> llenarTableReservacion(){
        ObservableList <reservacion> lista = FXCollections.observableArrayList ();
        try {
            CONNECTION.conectar();
            String consulta = "{call  obtenerReservacion ()}";
            PreparedStatement ingreso = CONNECTION.getConnection().prepareStatement(consulta);
            ResultSet resultado = ingreso.executeQuery();
            while (resultado.next()) {
                lista.add(
                        new reservacion(
                                resultado.getInt("idReserva"),
                                resultado.getDate("fechaInicio"),
                                resultado.getDate("fechaFin"),
                                resultado.getString("detalle"),
                                resultado.getFloat("total"),
                                resultado.getBoolean("estado"),
                                resultado.getString("idCliente"),
                                resultado.getString("numeroHabitacion")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return lista;
    }
    
    public boolean actualizarReservacion(){
        
        try {
            CONNECTION.conectar();
            CallableStatement sp = CONNECTION.getConnection().prepareCall(actualizar);
            sp.setInt(1, this.getId());
            sp.setDate(2, this.getFechaInicio());
            sp.setDate(3, this.getFechaFin());
            sp.setString(4, this.getDetalle());
            sp.setFloat(5, this.getTotal());
            sp.setString(6, this.getCliente());
            sp.setString(7, this.getHabitacion());
            sp.setBoolean(8, this.isEstado());
            sp.execute();
            sp.close();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return false;
    }
    public boolean ingresarReservacion(){
        try {
            CONNECTION.conectar();
            PreparedStatement sp = CONNECTION.getConnection().prepareStatement(ingresar);
            sp.setInt(1, this.getId());
            sp.setDate(2, this.getFechaInicio());
            sp.setDate(3, this.getFechaFin());
            sp.setString(4, this.getDetalle());
            sp.setFloat(5, this.getTotal());
            sp.setString(6, this.getCliente());
            sp.setString(7, this.getHabitacion());
            sp.setBoolean(8, this.isEstado());
            sp.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return false;
    }
    
    public boolean eliminarReservacion(){
        toString();
        try {
            CONNECTION.conectar();
            CallableStatement sp = CONNECTION.getConnection().prepareCall(eliminar);
            sp.setInt(1, this.getId());
            sp.execute();
            sp.close();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return false;
    }
    
    public ObservableList<reservacion> llenarTableReservacionCliente(){
        ObservableList <reservacion> lista = FXCollections.observableArrayList ();
        try {
            CONNECTION.conectar();
            String consulta = "{call  obtenerReservacion ()}";
            PreparedStatement ingreso = CONNECTION.getConnection().prepareStatement(consulta);
            ResultSet resultado = ingreso.executeQuery();
            while (resultado.next()) {
                if(idclien.equals(resultado.getString("idCliente"))){
                   lista.add(
                        new reservacion(
                                resultado.getInt("idReserva"),
                                resultado.getDate("fechaInicio"),
                                resultado.getDate("fechaFin"),
                                resultado.getString("detalle"),
                                resultado.getFloat("total"),
                                resultado.getBoolean("estado"),
                                resultado.getString("idCliente"),
                                resultado.getString("numeroHabitacion"))); 
                }
                
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return lista;
    }
}
