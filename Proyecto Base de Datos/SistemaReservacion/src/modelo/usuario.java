
package modelo;

import conexion.DBConnection;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;

public class usuario {
    protected static final DBConnection CONNECTION = DBConnection.getInstance();
    protected String id;
    protected String usuario;
    protected String contrasena;

    public usuario(String id, String usuario, String contrasena) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public usuario() {
    }

    public String getIdEmpleado() {
        return id;
    }

    public void setIdEmpleado(String id) {
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
    
    public String login(){
        try {
            CONNECTION.conectar();
            String consulta = "{call  login (?,?,?,?,?)}";
            CallableStatement sp = CONNECTION.getConnection().prepareCall(consulta);
            sp.setString(1, this.getUsuario());
            sp.setString(2, this.getContrasena());
            sp.registerOutParameter(3, Types.VARCHAR);
            sp.registerOutParameter(4, Types.VARCHAR);
            sp.registerOutParameter(5, Types.VARCHAR);
            sp.execute();
            String rol = sp.getString(3);
            String idC = sp.getString(4);
            String idE = sp.getString(5);
            if(idC != null){
                this.setUsuario(idC);
            }
            else if(idE != null){
                this.setUsuario(idE);
            }
            sp.close();
            return rol;
        } catch (SQLException ex) {
           Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            CONNECTION.desconectar();
        }
    }
    
}
