package modelo;

import javax.persistence.Entity;

/**
 *
 * @author Panella Lautaro
 */
@Entity
public class Empleado extends Persona implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int nroLegajo;
    private double sueldo;

    public Empleado() {
    }

    public Empleado(int nroLegajo, double sueldo, String nombre, String apellido, long dni, Domicilio domicilio) {
        super(nombre, apellido, dni, domicilio);
        this.nroLegajo = nroLegajo;
        this.sueldo = sueldo;
    }

    public int getNroLegajo() {
        return nroLegajo;
    }

    public void setNroLegajo(int nroLegajo) {
        this.nroLegajo = nroLegajo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
}
