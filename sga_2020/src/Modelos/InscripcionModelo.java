
package Modelos;

import DataBase.InscripcionDAO;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTable;


/**
 *
 * @Panella
 */
public class InscripcionModelo {
    private long codigo;
    private String nombre;
    private Date fecha;
    private long codigoCarrera;
    private InscripcionDAO inscripcionDAO = new InscripcionDAO();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public long getCodigoCarrera() {
        return codigoCarrera;
    }

    public void setCodigoCarrera(long codigoCarrera) {
        this.codigoCarrera = codigoCarrera;
    }

    public InscripcionDAO getInscripcionDAO() {
        return inscripcionDAO;
    }

    public void setInscripcionDAO(InscripcionDAO inscripcionDAO) {
        this.inscripcionDAO = inscripcionDAO;
    }
    
    public boolean agregarDatos(InscripcionModelo inscripcion) {
        return inscripcionDAO.agregarDatosDAO(inscripcion);
    }
    
    public ArrayList<InscripcionModelo> traerDatos() {
        return inscripcionDAO.traerDatosDAO();
    }
    
    public Set<String> traerCodigoCarrera(){
        return inscripcionDAO.traerCodigoCarreraDAO();
    }
    
    public boolean modificarDatos(InscripcionModelo inscripcion) {
        return inscripcionDAO.modificarDatosDAO(inscripcion);
    }

    public boolean eliminarDatos(JTable tabla) {
        return inscripcionDAO.eliminarDatosDAO(tabla);
    }
    
    // Valido que se ingresen caracteres.
    public boolean validarIngreso(String texto) {
        if (texto.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
