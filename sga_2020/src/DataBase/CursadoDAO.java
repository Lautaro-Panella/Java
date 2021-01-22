
package DataBase;

import Modelos.CursadoModelo;
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
public class CursadoDAO extends SQLQuery {
    private CursadoModelo cursado;
    private ArrayList<CursadoModelo> cursados;

    public ArrayList<CursadoModelo> getCursados() {
        return cursados;
    }

    public void setCursados(ArrayList<CursadoModelo> cursados) {
        this.cursados = cursados;
    }

    // Envío datos a mi db.
    public boolean agregarDatosDAO(CursadoModelo cursado) {  
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String query = "INSERT INTO cursado (cur_alu_dni, cur_mat_cod, cur_nota) VALUES (?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement(query);
            preparedStmt.setLong(1, cursado.getDniAlumno());
            preparedStmt.setLong(2, cursado.getCodigoMateria());
            preparedStmt.setLong(3, cursado.getNota());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CursadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Traigo datos desde mi db.
    public ArrayList<Modelos.CursadoModelo> traerDatosDAO() {  
        cursados = new ArrayList();
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select * from cursado");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                cursado = new Modelos.CursadoModelo();
                cursado.setDniAlumno(resultado.getLong(1));
                cursado.setCodigoMateria(resultado.getLong(2));
                cursado.setNota(resultado.getLong(3));
                cursados.add(cursado);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CursadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cursados;
    }
    
    // Modifico datos de mi db.
    public boolean modificarDatosDAO(Modelos.CursadoModelo cursado) {   
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement("UPDATE cursado SET cur_nota=? WHERE cur_alu_dni=? AND cur_mat_cod=?");
            preparedStmt.setLong(1, cursado.getNota());
            preparedStmt.setLong(2, cursado.getDniAlumno());
            preparedStmt.setLong(3, cursado.getCodigoMateria());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CursadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Elimino datos de mi db.
    public boolean eliminarDatosDAO(JTable tabla) { 
        int seleccion;
        cursados = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("DELETE FROM cursado WHERE cur_alu_dni=? AND cur_mat_cod=?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setLong(1, cursados.get(seleccion).getDniAlumno());
            this.consulta.setLong(2, cursados.get(seleccion).getCodigoMateria());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CursadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Consulto a la base de datos los DNI de los alumnos.
    public Set<String> traerDNIalumnoDAO() {   
        Set<String> dniAlumno = new HashSet<>(); 
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select alu_dni from alumno");
            ResultSet resultados = consulta.executeQuery();
            dniAlumno.add("");
            while (resultados.next()) {
                dniAlumno.add(Long.toString(resultados.getLong(1)));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CursadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dniAlumno;
    }
    
    // Consulto a la base de datos los códigos de las materias.
    public Set<String> traerCodigoMateriaDAO() {    
        Set<String> codigoMateria = new HashSet<>();   
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select mat_cod from materia");
            ResultSet resultado = consulta.executeQuery();
            codigoMateria.add("");
            while (resultado.next()) {
                codigoMateria.add(Long.toString(resultado.getLong(1)));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CursadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigoMateria;
    }    
}
