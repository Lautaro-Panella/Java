/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.ConfigHibernate;
import java.sql.Connection;
import Conexion.DataBase;
import Modelo.*;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @ Panella Lautaro
 */
public class GestorComputadora extends Gestor {
    
    DataBase db = new DataBase();
    Connection conexion = db.estableceConexion();

    public GestorComputadora() {
        sesion = ConfigHibernate.openSession();
    }
    
    public void insertarComputadora(String codigo, String marca, String modelo) {
        try {
            Computadora computadora = new Computadora();
            computadora.setCodigo(codigo);
            computadora.setMarca(marca);
            computadora.setModelo(modelo);
            this.guardar(computadora);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }  
    
    public void modificarComputadora(Long id, String codigo, String marca, String modelo) {
        try {
            Computadora computadora = this.dameComputadora(id);
            computadora.setCodigo(codigo);
            computadora.setMarca(marca);
            computadora.setModelo(modelo);
            this.guardar(computadora);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void eliminarComputadora(Long id) {
        try {
            Computadora computadora = this.dameComputadora(id);
            this.eliminar(computadora);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Computadora> dameListaComputadoras() {
        try {
            Query consulta = sesion.createQuery("FROM Computadora");
            List<Computadora> lista;
            lista = consulta.list();
            return lista;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }   
    
    public Computadora dameComputadora(Long id) {
        try {
            Query consulta = sesion.createQuery("FROM Computadora WHERE id = :idParam");
            consulta.setParameter("idParam", id);
            Computadora computadora = (Computadora) consulta.uniqueResult();
            return computadora;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void cierraConexion() {
        try {
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
