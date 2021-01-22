
package DataBase;

import Modelos.ProfesorModelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @Panella
 */
public class ProfesorDAO extends SQLQuery{
    private ArrayList<ProfesorModelo> profesores;
    private ProfesorModelo profesor;

    public ArrayList<ProfesorModelo> getProfesores() {
        return profesores;
    }

    public void setProfesores(ArrayList<ProfesorModelo> profesores) {
        this.profesores = profesores;
    }

    public boolean agregarDatosDAO(ProfesorModelo profesor) { 
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String query = "INSERT INTO profesor (prof_dni, prof_nombre, prof_apellido, prof_fec_nac, prof_domicilio, prof_telefono) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement(query);
            preparedStmt.setLong(1, profesor.getDni());
            preparedStmt.setString(2, profesor.getNombre());
            preparedStmt.setString(3, profesor.getApellido());
            preparedStmt.setDate(4, profesor.getFechaNac());
            preparedStmt.setString(5, profesor.getDomicilio());
            preparedStmt.setString(6, profesor.getTelefono());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<ProfesorModelo> traerDatosDAO() {  
        profesores = new ArrayList();
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select * from profesor");
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {
                profesor = new Modelos.ProfesorModelo();
                profesor.setDni(resultado.getLong(1));
                profesor.setNombre(resultado.getString(2));
                profesor.setApellido(resultado.getString(3));
                profesor.setFechaNac(resultado.getDate(4));
                profesor.setDomicilio(resultado.getString(5));
                profesor.setTelefono(resultado.getString(6));
                profesores.add(profesor);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profesores;
    }
    
    public boolean modificarDatosDAO(ProfesorModelo profesor) { 
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement("UPDATE profesor SET prof_nombre=?, prof_apellido=?, prof_fec_nac=?, prof_domicilio=?, prof_telefono=? WHERE prof_dni=?");
            preparedStmt.setString(1, profesor.getNombre());
            preparedStmt.setString(2, profesor.getApellido());
            preparedStmt.setDate(3, profesor.getFechaNac());
            preparedStmt.setString(4, profesor.getDomicilio());
            preparedStmt.setString(5, profesor.getTelefono());
            preparedStmt.setLong(6, profesor.getDni());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean eliminarDatosDAO(JTable tabla) {  
        int seleccion;
        profesores = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("DELETE FROM profesor WHERE prof_dni = ?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setLong(1, profesores.get(seleccion).getDni());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean dniUnicoDAO(ProfesorModelo profesor) { 
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select * from profesor where prof_dni=?");
            this.consulta.setLong(1, profesor.getDni());
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                return false;
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProfesorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }    
}
