
package DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import Modelos.CarreraModelo;

/**
 *
 * @Panella
 */
public class CarreraDAO extends SQLQuery{
    private CarreraModelo carrera;
    private ArrayList<CarreraModelo> carreras;
    
    public ArrayList<CarreraModelo> getCarreras() {
        return carreras;
    }

    public void setCarreras(ArrayList<CarreraModelo> carreras) {
        this.carreras = carreras;
    }
    
    // Envío datos a mi db.
    public boolean agregarDatosDAO(CarreraModelo carrera) { 
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            this.datos = this.consulta.executeQuery();
            String query = "INSERT INTO carrera (car_nombre, car_duracion) VALUES (?,?)";
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement(query);
            preparedStmt.setString(1, carrera.getNombre());
            preparedStmt.setLong(2, carrera.getDuracion());
            preparedStmt.execute();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // Traigo datos desde mi db.
    public ArrayList<CarreraModelo> traerDatosDAO() {
        carreras = new ArrayList();
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select * from carrera");
            ResultSet resultado = consulta.executeQuery();
            while (resultado.next()) {
                carrera = new CarreraModelo();
                carrera.setCodigo(resultado.getLong(1));
                carrera.setNombre(resultado.getString(2));
                carrera.setDuracion(resultado.getLong(3));
                carreras.add(carrera);
            }
            this.desconectar();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return carreras;
    }
    
    // Modifico datos de mi db.
    public boolean modificarDatosDAO(CarreraModelo carrera) {
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            PreparedStatement preparedStmt = (PreparedStatement) this.connection.prepareStatement("UPDATE carrera SET car_nombre=?, car_duracion=? WHERE car_cod=?");
            preparedStmt.setString(1, carrera.getNombre());
            preparedStmt.setLong(2, carrera.getDuracion());
            preparedStmt.setLong(3, carrera.getCodigo());
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
        carreras = new ArrayList(traerDatosDAO());
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("DELETE FROM carrera WHERE car_cod = ?");
            seleccion = tabla.getSelectedRow();
            this.consulta.setLong(1, carreras.get(seleccion).getCodigo());
            consulta.executeUpdate();
            this.desconectar();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // Consulto a la base de datos los nombres de las carreras.
    public boolean carreraUnicaDAO(CarreraModelo carrera) {
        try {
            this.conectar("127.0.0.1", "SGA_2020", "root", "1234");
            this.consulta = this.connection.prepareStatement("select * from carrera where car_nombre=?");
            this.consulta.setString(1, carrera.getNombre());
            ResultSet resultado = consulta.executeQuery();
            if (resultado.next()) {
                return false;
            }
            this.desconectar();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CarreraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
