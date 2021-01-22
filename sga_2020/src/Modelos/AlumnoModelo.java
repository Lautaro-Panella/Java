
package Modelos;

import java.sql.Date;
import DataBase.AlumnoDAO;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @Panella
 */
public class AlumnoModelo {
    private long dni;
    private String nombre;
    private String apellido;
    private Date fechaNac;
    private String domicilio;
    private String telefono;
    private long codInsc;
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
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
    
    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public long getCodInsc() {
        return codInsc;
    }

    public void setCodInsc(long codInsc) {
        this.codInsc = codInsc;
    }
    
    public Set<String> traerCodInsc(){
        return alumnoDAO.traerCodInscDAO();
    }

    public boolean agregarDatos(AlumnoModelo alumno) {
        return alumnoDAO.agregarDatosDAO(alumno);
    }

    public ArrayList<AlumnoModelo> traerDatos() {
        return alumnoDAO.traerDatosDAO();
    }
    
    public boolean modificarDatos(AlumnoModelo alumno) {
        return alumnoDAO.modificarDatosDAO(alumno);
    }

    public boolean eliminarDatos(JTable tabla) {
        return alumnoDAO.eliminarDatosDAO(tabla);
    }
    
    // Valido que se ingresen caracteres.
    public boolean validarIngreso(String texto) {
        if (texto.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    } 
    
    // Valido que se ingresen números.
    public boolean validarIngresoDNI(String numeros) {
        try {
            dni = Long.parseLong(numeros);
            return false;
        } catch (NumberFormatException nfe) {
            return true;
        }
    }
    
    // Valido que el DNI del alumno no exista.
    public boolean dniUnico(AlumnoModelo alumno) {
        return alumnoDAO.dniUnicoDAO(alumno);
    } 
}