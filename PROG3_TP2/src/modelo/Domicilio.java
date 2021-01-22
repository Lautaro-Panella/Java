package modelo;

import javax.persistence.Entity;

/**
 *
 * @author Panella Lautaro
 */
@Entity
public class Domicilio extends EntityBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private String localidad;
    private String calle;
    private int numero;
    private Persona persona;

    public Domicilio() {
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        persona.setDomicilio(this);
    }
}
