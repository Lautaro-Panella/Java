/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.DataBase;
import Modelo.Componente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @ Panella Lautaro
 */
public class GestorComponente {
    
    DataBase db = new DataBase();
    Connection conexion = db.estableceConexion();
    
    public List<Componente> dameListaComponentes() {
        ResultSet rs = null;
        Componente c1 = new Componente();
        GestorComputadora gc = new GestorComputadora();
        List<Componente> componentes = new ArrayList<>();
        try {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = s.executeQuery("SELECT * FROM componente");
            while (rs.next()) {
                c1 = new Componente();
                c1.setId(rs.getLong("id"));
                c1.setNombre(rs.getString("nombre"));
                c1.setNroSerie(rs.getString("nroSerie"));
                c1.setComputadora(gc.dameComputadora(rs.getLong("idComputadora")));
                componentes.add(c1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return componentes;
    }  
    
    public void insertarComponente(String nombre, String nroSerie, Long idComputadora) {
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO componente (nombre, nroSerie, idComputadora) VALUES (?, ?, ?)");
            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, nombre);
            ps.setString(2, nroSerie);
            ps.setLong(3, idComputadora);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarComponente(Long id, String nombre, String nroSerie, Long idComputadora) {
        try {
            PreparedStatement ps = conexion.prepareStatement("UPDATE componente SET nombre = ?, nroSerie = ?, idComputadora = ? where id = ?");
            // Se establecen los parámetros y se ejecuta la sentencia.
            ps.setString(1, nombre);
            ps.setString(2, nroSerie);
            ps.setLong(3, idComputadora);
            ps.setLong(4, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarComponente(Long id) {
        try {
            Statement st = conexion.createStatement();
            String sql = "DELETE FROM componente WHERE id = " + id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Componente dameComponente(Long id) {
        ResultSet rs = null;
        Componente c = new Componente();
        GestorComputadora gc = new GestorComputadora();
        try {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = s.executeQuery("select * from componente where id = " + id);
            if (rs.next()) {
                c.setId(rs.getLong("id"));
                c.setNombre(rs.getString("nombre"));
                c.setNroSerie(rs.getString("nroSerie"));
                c.setComputadora(gc.dameComputadora(rs.getLong("idComputadora")));    
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
    public List<String> dameIdComputadoras() {
        List<String> idComputadoras = new ArrayList<>();
        ResultSet rs = null;
        try {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = s.executeQuery("SELECT id FROM computadora");
            while (rs.next()) {
                idComputadoras.add(Long.toString(rs.getLong(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idComputadoras;
    }
    
    public void cierraConexion() {
        try {
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
