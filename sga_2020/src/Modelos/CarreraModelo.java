
package Modelos;

import DataBase.CarreraDAO;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @Panella
 */
public class CarreraModelo {
    private long codigo;
    private String nombre;
    private long duracion;
    private CarreraDAO carreraDAO = new CarreraDAO();

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long duracion) {
        this.duracion = duracion;
    }
    
    public boolean agregarDatos(CarreraModelo carrera) {
        return carreraDAO.agregarDatosDAO(carrera);
    }
    
    public ArrayList<Modelos.CarreraModelo> traerDatos() {
        return carreraDAO.traerDatosDAO();
    }
    
    public boolean modificarDatos(CarreraModelo carrera) {
        return carreraDAO.modificarDatosDAO(carrera);
    }

    public boolean eliminarDatos(JTable tabla) {
        return carreraDAO.eliminarDatosDAO(tabla);
    }

    // Valido que el nombre de la carrera no exista.
    public boolean carreraUnica(CarreraModelo carrera) {
        return carreraDAO.carreraUnicaDAO(carrera);
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
