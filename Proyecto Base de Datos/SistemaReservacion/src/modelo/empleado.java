
package modelo;

import conexion.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class empleado extends usuario{
    protected static final DBConnection CONNECTION = DBConnection.getInstance();
    protected static final Logger LOGGER = Logger.getLogger("Usuario Logger");
    private final String actualizar = "{call  actualizarEmpleados (?,?,?,?,?,?,?,?,?,?,?)}";
    private final String ingresar = "{call   ingresarEmpleados (?,?,?,?,?,?,?,?,?,?,?)}";
    private final String eliminar = "{call   eliminarEmpleados (?)}";
    protected String idEmpleado;
    protected String nombre;
    protected String apellido;
    protected String direccion;
    protected String telefono;
    protected float salario;
    protected boolean estado;
    protected String turno;
    protected String tipo;
    public static String admin;
    public static int idUsu;

    public empleado(String id, String usuario, String contrasena) {
        super(id, usuario, contrasena);
    }
    
     public empleado(String idEmpleado, String nombre, String apellido, String direccion, String telefono, float salario, 
             boolean estado, String turno, String tipo, String id, String usuario, String contrasena) {
        super(id, usuario, contrasena);
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.salario = salario;
        this.estado = estado;
        this.turno = turno;
        this.tipo = tipo;
    }

    public empleado() {
    }
   
    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        ObservableList <empleado> listaE =llenarTableEmpleado();
        for(empleado e : listaE){
            if(u.getUsuario().equals(e.getIdEmpleado())){
                admin=e.getNombre()+" "+e.getApellido();
            }
        }
    }
    public ObservableList<empleado> llenarTableEmpleado(){
        ObservableList <empleado> lista = FXCollections.observableArrayList ();
        try {
            CONNECTION.conectar();
            String consulta = "{call  obtenerEmpleados ()}";
            PreparedStatement ingreso = CONNECTION.getConnection().prepareStatement(consulta);
            ResultSet resultado = ingreso.executeQuery();
            while (resultado.next()) {
                lista.add(
                        new empleado(
                                resultado.getString("idEmpleado"),
                                resultado.getString("nombre"),
                                resultado.getString("apellido"),
                                resultado.getString("direccion"),
                                resultado.getString("telefono"),
                                resultado.getFloat("sueldo"),
                                resultado.getBoolean("estado"),
                                resultado.getString("turno"),
                                resultado.getString("tipo"),
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

    public boolean actualizarEmpleado(){
        try {
            CONNECTION.conectar();
            CallableStatement sp = CONNECTION.getConnection().prepareCall(actualizar);
            sp.setString(1, this.getIdEmpleado());
            sp.setString(2, this.getNombre());
            sp.setString(3, this.getApellido());
            sp.setString(4, this.getDireccion());
            sp.setString(5, this.getTelefono());
            sp.setBoolean(6, this.isEstado());
            sp.setFloat(7, this.getSalario());
            sp.setString(8, this.getTurno());
            sp.setString(9, this.getTipo());
            sp.setString(10, this.getUsuario());
            sp.setString(11, this.getContrasena());
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
    public boolean ingresarEmpleado(){
        try {
            CONNECTION.conectar();
            PreparedStatement sp = CONNECTION.getConnection().prepareStatement(ingresar);
            sp.setString(1, this.getIdEmpleado());
            sp.setString(2, this.getNombre());
            sp.setString(3, this.getApellido());
            sp.setString(4, this.getDireccion());
            sp.setString(5, this.getTelefono());
            sp.setBoolean(6, this.isEstado());
            sp.setFloat(7, this.getSalario());
            sp.setString(8, this.getTurno());
            sp.setString(9, this.getTipo());
            sp.setString(10, this.getUsuario());
            sp.setString(11, this.getContrasena());
            sp.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        } finally {
            CONNECTION.desconectar();
        }
        return false;
    }
    
    public boolean eliminarEmpleado(){
        try {
            CONNECTION.conectar();
            CallableStatement sp = CONNECTION.getConnection().prepareCall(eliminar);
            sp.setString(1, this.getIdEmpleado());
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

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
    
}
