/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @ Panella Lautaro
 */
public class DataBase {
    
    // Parámetros de conexión:
    private Connection conexion = null;
    private String user = "root";
    private String password = "1234";
    private String puerto = "3306";
    private String catalogo = "prog3_tp3.1";
    private String host = "localhost";

    // Se establece la conexión con la base de datos:
    public Connection estableceConexion() {
        if (conexion != null) {
            return conexion;
        }
        try {
            // Se registra el Driver de MySQL:
            Class.forName("com.mysql.jdbc.Driver");
            String urlConexion = "jdbc:mysql://" + host + ":" + puerto + "/" + catalogo;
            conexion = DriverManager.getConnection(urlConexion, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }

    // Se cierra la conexión con la base de datos:
    public void cierraConexion() {
        try {
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
