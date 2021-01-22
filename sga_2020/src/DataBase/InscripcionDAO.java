
package DataBase;

import Modelos.InscripcionModelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @Panella
 */
public class InscripcionDAO extends SQLQuery {
    private InscripcionModelo inscripcion;
    private ArrayList<InscripcionModelo> inscripciones;

    public ArrayList<InscripcionModelo> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(ArrayList<InscripcionModelo> inscripciones) {
        this.inscripciones = inscripciones;
    }
    
    // Envío datos a mi db.
    public boolean agregarDatosDAO(InscripcionModelo inscripcion) {  
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String query = "INSERT INTO inscripcion (insc_nombre, insc_fecha, insc_car_cod) VALUES (?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement(query);
            preparedStmt.setString(1, inscripcion.getNombre());
            preparedStmt.setDate(2, inscripcion.getFecha());
            preparedStmt.setLong(3, inscripcion.getCodigoCarrera());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // Traigo datos desde mi db.
    public ArrayList<InscripcionModelo> traerDatosDAO() {   
        inscripciones = new ArrayList();
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select * from inscripcion");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                inscripcion = new Modelos.InscripcionModelo();
                inscripcion.setCodigo(resultado.getLong(1));
                inscripcion.setNombre(resultado.getString(2));
                inscripcion.setFecha(resultado.getDate(3));
                inscripcion.setCodigoCarrera(resultado.getLong(4));
                inscripciones.add(inscripcion);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inscripciones;
    }
    
    // Modifico datos de mi db.
    public boolean modificarDatosDAO(InscripcionModelo inscripcion) {   
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement("UPDATE inscripcion SET insc_nombre=?, insc_fecha=?, insc_car_cod=? WHERE insc_cod=?");
            preparedStmt.setString(1, inscripcion.getNombre());
            preparedStmt.setDate(2, inscripcion.getFecha());
            preparedStmt.setLong(3, inscripcion.getCodigoCarrera());
            preparedStmt.setLong(4, inscripcion.getCodigo());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Elimino datos de mi db.
    public boolean eliminarDatosDAO(JTable tabla) { 
        int seleccion;
        inscripciones = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("DELETE FROM inscripcion WHERE insc_cod = ?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setLong(1, inscripciones.get(seleccion).getCodigo());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // Traigo los códigos de carrera desde mi db.
    public Set<String> traerCodigoCarreraDAO() {   
        Set<String> codCarrera = new HashSet<>();  
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select car_cod from carrera");
            ResultSet resultado = consulta.executeQuery();
            codCarrera.add("");
            while (resultado.next()) {
                codCarrera.add(Long.toString(resultado.getLong(1)));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InscripcionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codCarrera;
    }   
}
