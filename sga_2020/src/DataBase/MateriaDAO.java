
package DataBase;

import Modelos.MateriaModelo;
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
public class MateriaDAO extends SQLQuery{
    private MateriaModelo materia;
    private ArrayList<MateriaModelo> materias;

    public ArrayList<MateriaModelo> getMaterias() {
        return materias;
    }

    public void setMaterias(ArrayList<MateriaModelo> materias) {
        this.materias = materias;
    }

    // Envío datos a mi db.
    public boolean agregarDatosDAO(MateriaModelo materia) { 
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String query = "INSERT INTO materia (mat_nombre, mat_prof_dni) VALUES (?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement(query);
            preparedStmt.setString(1, materia.getNombre());
            preparedStmt.setLong(2, materia.getDniProf());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Traigo datos desde mi db.
    public ArrayList<MateriaModelo> traerDatosDAO() { 
        materias = new ArrayList();
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select * from materia");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                materia = new MateriaModelo();
                materia.setCodigo(resultado.getLong(1));
                materia.setNombre(resultado.getString(2));
                materia.setDniProf(resultado.getLong(3));
                materias.add(materia);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }
    
    // Modifico datos de mi db.
    public boolean modificarDatosDAO(MateriaModelo materia) {   
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement("UPDATE materia SET mat_nombre=?, mat_prof_dni=? WHERE mat_cod=?");
            preparedStmt.setString(1, materia.getNombre());
            preparedStmt.setLong(2, materia.getDniProf());
            preparedStmt.setLong(3, materia.getCodigo());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Elimino datos de mi db.
    public boolean eliminarDatosDAO(JTable tabla) { 
        int seleccion;
        materias = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("DELETE FROM materia WHERE mat_cod = ?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setLong(1, materias.get(seleccion).getCodigo());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // Consulto a la base de datos los DNI de los profesores.
    public Set<String> traerDNIProfDAO() {   
        Set<String> dniProf = new HashSet<>(); 
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select prof_dni from profesor");
            ResultSet resultado = consulta.executeQuery();
            dniProf.add("");
            while (resultado.next()) {
                dniProf.add(Long.toString(resultado.getLong(1)));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MateriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dniProf;
    }    
}
