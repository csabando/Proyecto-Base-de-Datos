
package modelo;

import conexion.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static modelo.empleado.LOGGER;

public class cliente extends usuario{
    protected static final DBConnection CONNECTION = DBConnection.getInstance();
    private final String actualizar = "{call  actualizarCliente (?,?,?,?,?,?,?)}";
    private final String ingresar = "{call   ingresarCliente (?,?,?,?,?,?,?)}";
    private final String eliminar = "{call   eliminarCliente (?)}";
    protected String idCliente;
    protected String nombre;
    protected String apellido;
    protected String direccion;
    protected String telefono;
    public static String clien;
    public static String idclien;
    public cliente() {
    }
    
    public cliente(String id, String usuario, String contrasena) {
        super(id, usuario, contrasena);
    }

    public cliente(String idCliente, String nombre, String apellido, String direccion, String telefono, String id, String usuario, String contrasena) {
        super(id, usuario, contrasena);
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public void obtenerUsuario(usuario u){
        ObservableList <cliente> listaE = obtenerCliente();
        for(cliente c : listaE){
            if(u.getUsuario().equals(c.getIdCliente())){
                clien=c.getNombre()+" "+c.getApellido();
                idclien = c.getIdCliente();
            }
        }
    }
    public ObservableList<cliente> obtenerCliente(){
        ObservableList <cliente> lista = FXCollections.observableArrayList ();
        try {
            CONNECTION.conectar();
            String consulta = "{call  obtenerclientes ()}";
            PreparedStatement ingreso = CONNECTION.getConnection().prepareStatement(consulta);
            ResultSet resultado = ingreso.executeQuery();
            while (resultado.next()) {
                lista.add(
                        new cliente(
                                resultado.getString("idCliente"),
                                resultado.getString("nombre"),
                                resultado.getString("apellido"),
                                resultado.getString("direccion"),
                                resultado.getString("telefono"),
                                String.valueOf(resultado.getInt("idUsuario")),
                                resultado.getString("usuario"),
                                resultado.getString("contrasena")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return lista;
    }
    
    public boolean actualizarCliente(){
        try {
            CONNECTION.conectar();
            CallableStatement sp = CONNECTION.getConnection().prepareCall(actualizar);
            sp.setString(1, this.getIdCliente());
            sp.setString(2, this.getNombre());
            sp.setString(3, this.getApellido());
            sp.setString(4, this.getDireccion());
            sp.setString(5, this.getTelefono());
            sp.setString(6, this.getUsuario());
            sp.setString(7, this.getContrasena());
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
    public boolean ingresarCliente(){
        try {
            CONNECTION.conectar();
            PreparedStatement sp = CONNECTION.getConnection().prepareStatement(ingresar);
            sp.setString(1, this.getIdCliente());
            sp.setString(2, this.getNombre());
            sp.setString(3, this.getApellido());
            sp.setString(4, this.getDireccion());
            sp.setString(5, this.getTelefono());
            sp.setString(6, this.getUsuario());
            sp.setString(7, this.getContrasena());
            sp.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return false;
    }
}
