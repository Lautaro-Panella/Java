
package Modelos;

import java.sql.Date;
import DataBase.ProfesorDAO;
import java.util.ArrayList;
import javax.swing.JTable;

public class ProfesorModelo {
    private String nombre;
    private String apellido;
    private Date fechaNac;
    private String domicilio;
    private String telefono;
    private long dni;
    private ProfesorDAO profesorDAO = new ProfesorDAO();

    public ProfesorModelo() {
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

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }

    public boolean agregarDatos(ProfesorModelo profesor) {
        return profesorDAO.agregarDatosDAO(profesor);
    }

    public ArrayList<Modelos.ProfesorModelo> traerDatos() {
        return profesorDAO.traerDatosDAO();
    }

    public boolean modifica(ProfesorModelo profesor) {
        return profesorDAO.modificarDatosDAO(profesor);
    }
    
    public boolean eliminarDatos(JTable tabla) {
        return profesorDAO.eliminarDatosDAO(tabla);
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
    public boolean dniUnico(ProfesorModelo profesor) {
        return profesorDAO.dniUnicoDAO(profesor);
    }
}
