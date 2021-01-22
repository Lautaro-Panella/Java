
package Modelos;

import DataBase.MateriaDAO;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @Panella
 */
public class MateriaModelo {
    private long codigo;
    private String nombre;
    private long dniProf;
    private MateriaDAO materiaDAO = new MateriaDAO();

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

    public long getDniProf() {
        return dniProf;
    }

    public void setDniProf(long dniProf) {
        this.dniProf = dniProf;
    }

    public MateriaDAO getInscripcionDAO() {
        return materiaDAO;
    }

    public void setInscripcionDAO(MateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }

    public Set<String> traerDNIProf(){
        return materiaDAO.traerDNIProfDAO();
    }

    public boolean agregarDatos(MateriaModelo materia) {
        return materiaDAO.agregarDatosDAO(materia);
    }

    public ArrayList<MateriaModelo> traerDatos() {
        return materiaDAO.traerDatosDAO();
    }
    
    public boolean modificarDatos(MateriaModelo modelo) {
        return materiaDAO.modificarDatosDAO(modelo);
    }

    public boolean eliminarDatos(JTable tabla) {
        return materiaDAO.eliminarDatosDAO(tabla);
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
