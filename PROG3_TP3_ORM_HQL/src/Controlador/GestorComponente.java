/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Conexion.ConfigHibernate;
import Conexion.DataBase;
import Modelo.Componente;
import Modelo.Computadora;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;

/**
 *
 * @ Panella Lautaro
 */
public class GestorComponente extends Gestor{
    
    DataBase db = new DataBase();
    Connection conexion = db.estableceConexion();

    public GestorComponente() {
        sesion = ConfigHibernate.openSession();
    }
    
    public List<Componente> dameListaComponentes() {
        try {
            Query consulta = sesion.createQuery("FROM Componente");
            List<Componente> lista;
            lista = consulta.list();
            return lista;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }  
    
    public void insertarComponente(String nombre, String nroSerie, Long idComputadora) {
        try {
            Componente componente = new Componente();
            GestorComputadora gc = new GestorComputadora();
            componente.setNombre(nombre);
            componente.setNroSerie(nroSerie);
            componente.setComputadora(gc.dameComputadora(idComputadora));
            this.guardar(componente);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void modificarComponente(Long id, String nombre, String nroSerie, Long idComputadora) {
        try {
            Componente componente = this.dameComponente(id);
            GestorComputadora gc = new GestorComputadora();
            componente.setNombre(nombre);
            componente.setNroSerie(nroSerie);
            componente.setComputadora(gc.dameComputadora(idComputadora));
            this.guardar(componente);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void eliminarComponente(Long id) {
        try {
            Componente componente = this.dameComponente(id);
            this.eliminar(componente);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public Componente dameComponente(Long id) {
        try {
            Query consulta = sesion.createQuery("FROM Componente WHERE id = :idParam");
            consulta.setParameter("idParam", id);
            Componente componente = (Componente) consulta.uniqueResult();
            return componente;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<String> dameIdComputadoras() {
        List<String> idComputadoras = new ArrayList<>();
        try {
            Query consulta = sesion.createQuery("FROM Computadora");
            List<Computadora> lista;
            lista = consulta.list();
            for (Computadora computadora : lista) {
                idComputadoras.add(String.valueOf(computadora.getId()));
            }   
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return idComputadoras;
    }
    
    public void cierraConexion() {
        try {
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
