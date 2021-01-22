/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioA;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class Expediente {
    private int id;
    private String letra;
    private int numero;
    private String descripcion;
    private String tipo;
    private String ambito;
    private Expediente expedientePadre;
    private List<Expediente> expedientesHijos =  new ArrayList<>();
    private List<Control> controles =  new ArrayList<>();

    public Expediente() {
    }

    public Expediente(int id, String letra, int numero, String descripcion, String tipo, String ambito, Expediente expedientePadre) {
        this.id = id;
        this.letra = letra;
        this.numero = numero;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.ambito = ambito;
        this.expedientePadre = expedientePadre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public Expediente getExpedientePadre() {
        return expedientePadre;
    }

    public void setExpedientePadre(Expediente expedientePadre) {
        this.expedientePadre = expedientePadre;
    }

    public List<Expediente> getExpedientesHijos() {
        return expedientesHijos;
    }

    public void setExpedientesHijos(List<Expediente> expedientesHijos) {
        this.expedientesHijos = expedientesHijos;
    }

    public List<Control> getControles() {
        return controles;
    }

    public void setControles(List<Control> controles) {
        this.controles = controles;
    } 
    
    public String getCaratulaExpediente() {
        return numero+"-"+letra+"-"+descripcion;
    }
    
    public String getControlesObligatorios() {
        String controlesObl = "";
        if (this.getControles() != null) {
            for (Control c : this.controles) {
                if (c.isEsObligatorio()) {
                    controlesObl += c.getDenominacion()+", ";
                }
            }
        }
        return controlesObl;
    }
    
    public boolean getEstadoControles() {
        boolean aprobado = true;
        if (this.getControles() != null) {
            for (Control c : this.controles) {
                if (c.isEsObligatorio()) {
                    if (!c.getEstadoControl().isAprobado()) {
                        aprobado = false;
                        break;
                    }
                }
            }
        }
        return aprobado;
    }
    
    public List<Expediente> listaExpedientes() {
        List<Expediente> listaRecursion = new ArrayList<Expediente>();
        listaExpedientesRecursiva(this, listaRecursion);
        return listaRecursion;
    }

    public void listaExpedientesRecursiva(Expediente exp, List<Expediente> lista) {
        lista.add(exp);
        if (exp.getExpedientesHijos() != null) {
            for (Expediente expHijos : exp.getExpedientesHijos()) {
                listaExpedientesRecursiva(expHijos, lista);
            }
        }
    }
}
