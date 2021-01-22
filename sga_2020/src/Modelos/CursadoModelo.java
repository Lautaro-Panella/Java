
package Modelos;

import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTable;
import DataBase.CursadoDAO;

/**
 *
 * @Panella
 */
public class CursadoModelo {
    private long dniAlumno;
    private long codigoMateria;
    private long nota;
    private CursadoDAO cursadoDAO = new CursadoDAO();

    public long getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(long dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    public long getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(long codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public long getNota() {
        return nota;
    }

    public void setNota(long nota) {
        this.nota = nota;
    }

    public CursadoDAO getCursadoDAO() {
        return cursadoDAO;
    }

    public void setCursadoDAO(CursadoDAO cursadoDAO) {
        this.cursadoDAO = cursadoDAO;
    }
    
    public boolean agregarDatos(CursadoModelo cursado) {
        return cursadoDAO.agregarDatosDAO(cursado);
    }
    
    public ArrayList<CursadoModelo> traerDatos() {
        return cursadoDAO.traerDatosDAO();
    }

    public boolean modificarDatos(CursadoModelo modelo) {
        return cursadoDAO.modificarDatosDAO(modelo);
    }
    
     public boolean eliminarDatos(JTable tabla) {
        return cursadoDAO.eliminarDatosDAO(tabla);
    }
     
    public Set<String> traerDNIalumno(){
        return cursadoDAO.traerDNIalumnoDAO();
    }
    
    public Set<String> traerCodigoMateria(){
        return cursadoDAO.traerCodigoMateriaDAO();
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
