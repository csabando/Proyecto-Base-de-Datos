
package modelo;

public class hotel {
    protected int idHotel;
    protected String nombre;
    protected String descripcion;
    protected String direccion;
    protected String ciudad;

    public hotel(int idHotel, String nombre, String descripcion, String direccion, String ciudad) {
        this.idHotel = idHotel;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    
    
    
}
