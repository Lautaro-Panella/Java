package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author Panella Lautaro
 */
@Entity
public class Persona extends EntityBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String nombre;
    private String apellido;
    private long dni;
    protected Domicilio domicilio;

    public Persona() {
    }

    public Persona(String nombre, String apellido, long dni, Domicilio domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
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

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idDomicilio")
    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
}
