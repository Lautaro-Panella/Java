
package DataBase;

import Modelos.AlumnoModelo;
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
public class AlumnoDAO extends SQLQuery {
    private AlumnoModelo alumno;
    private ArrayList<AlumnoModelo> alumnos;
    
    public ArrayList<AlumnoModelo> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(ArrayList<AlumnoModelo> alumnos) {
        this.alumnos = alumnos;
    }

    // Envío datos a mi db.
    public boolean agregarDatosDAO(AlumnoModelo alumno) {  
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String query = "INSERT INTO alumno (alu_nombre, alu_apellido, alu_fec_nac, alu_domicilio, alu_telefono, alu_insc_cod, alu_dni) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement(query);
            preparedStmt.setString(1, alumno.getNombre());
            preparedStmt.setString(2, alumno.getApellido());
            preparedStmt.setDate(3, alumno.getFechaNac());
            preparedStmt.setString(4, alumno.getDomicilio());
            preparedStmt.setString(5, alumno.getTelefono());
            preparedStmt.setLong(6, alumno.getCodInsc());
            preparedStmt.setLong(7, alumno.getDni());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Traigo datos desde mi db.
    public ArrayList<AlumnoModelo> traerDatosDAO() {   
        alumnos = new ArrayList();
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select * from alumno");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                alumno = new Modelos.AlumnoModelo();
                alumno.setDni(resultado.getLong(1));
                alumno.setNombre(resultado.getString(2));
                alumno.setApellido(resultado.getString(3));
                alumno.setFechaNac(resultado.getDate(4));
                alumno.setDomicilio(resultado.getString(5));
                alumno.setTelefono(resultado.getString(6));
                alumno.setCodInsc(resultado.getLong(7));
                alumnos.add(alumno);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumnos;
    }

    // Modifico datos de mi db.
    public boolean modificarDatosDAO(Modelos.AlumnoModelo alumno) { 
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement("UPDATE alumno SET alu_nombre=?, alu_apellido=?, alu_fec_nac=?, alu_domicilio=?, alu_telefono=?, alu_insc_cod=? WHERE alu_dni=?");
            preparedStmt.setString(1, alumno.getNombre());
            preparedStmt.setString(2, alumno.getApellido());
            preparedStmt.setDate(3, alumno.getFechaNac());
            preparedStmt.setString(4, alumno.getDomicilio());
            preparedStmt.setString(5, alumno.getTelefono());
            preparedStmt.setLong(6, alumno.getCodInsc());
            preparedStmt.setLong(7, alumno.getDni());
            preparedStmt.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // Elimino datos de mi db.
    public boolean eliminarDatosDAO(JTable tabla) {  
        int seleccion;
        alumnos = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("DELETE FROM alumno WHERE alu_dni = ?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setLong(1, alumnos.get(seleccion).getDni());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Valido que el DNI del alumno no exista.
    public boolean dniUnicoDAO(Modelos.AlumnoModelo alumno) {  
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select * from alumno where alu_dni=?");
            this.consulta.setLong(1, alumno.getDni());
            ResultSet resultados = consulta.executeQuery();
            if (resultados.next()) {
                return false;
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    // Consulto a la base de datos los códigos de inscripción.
    public Set<String> traerCodInscDAO() {    
        Set<String> codInsc = new HashSet<>();   
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select insc_cod from inscripcion");
            ResultSet resultado = consulta.executeQuery();
            codInsc.add("");
            while (resultado.next()) {
                codInsc.add(Long.toString(resultado.getLong(1)));
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AlumnoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codInsc;
    }
}
