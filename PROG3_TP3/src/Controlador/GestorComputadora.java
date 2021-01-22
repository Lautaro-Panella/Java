/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Conexion.DataBase;
import Modelo.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ Panella Lautaro
 */
public class GestorComputadora {
    
    DataBase db = new DataBase();
    Connection conexion = db.estableceConexion();
    
    public void insertarComputadora(String codigo, String marca, String modelo) {
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO computadora (codigo, marca, modelo) VALUES (?, ?, ?)");
            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, codigo);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public void modificarComputadora(Long id, String codigo, String marca, String modelo) {
        try {
            PreparedStatement ps = conexion.prepareStatement("UPDATE computadora SET codigo = ?, marca = ?, modelo = ? where id = ?");
            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, codigo);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setLong(4, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarComputadora(Long id) {
        try {
            Statement st = conexion.createStatement();
            String sql = "DELETE FROM computadora WHERE id = " + id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Computadora> dameListaComputadoras() {
        ResultSet rs = null;
        Computadora c1 = new Computadora();
        List<Computadora> computadoras = new ArrayList<>();
        try {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = s.executeQuery("SELECT * FROM computadora");
            while (rs.next()) {
                c1 = new Computadora();
                c1.setId(rs.getLong("id"));
                c1.setCodigo(rs.getString("codigo"));
                c1.setMarca(rs.getString("marca"));
                c1.setModelo(rs.getString("modelo"));
                computadoras.add(c1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return computadoras;
    }   
    
    public Computadora dameComputadora(Long id) {
        ResultSet rs = null;
        Computadora c = new Computadora();
        try {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = s.executeQuery("select * from computadora where id = " + id);
            if (rs.next()) {
                c.setId(rs.getLong("id"));
                c.setCodigo(rs.getString("codigo"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));     
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public void cierraConexion() {
        try {
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
