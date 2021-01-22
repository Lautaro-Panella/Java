/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjercicioC;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ Panella Lautaro, TSP - TT.
 */
public class Sector {
    private int numero;
    private String denominacion;
    private String tipo;
    private Sector sectorPadre;
    private List<Sector> sectoresHijos = new ArrayList<>();
    private List<Persona> personas = new ArrayList<>();

    public Sector() {
    }

    public Sector(int numero, String denominacion, String tipo, Sector sectorPadre) {
        this.numero = numero;
        this.denominacion = denominacion;
        this.tipo = tipo;
        this.sectorPadre = sectorPadre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Sector getSectorPadre() {
        return sectorPadre;
    }

    public void setSectorPadre(Sector sectorPadre) {
        this.sectorPadre = sectorPadre;
    }

    public List<Sector> getSectoresHijos() {
        return sectoresHijos;
    }

    public void setSectoresHijos(List<Sector> sectoresHijos) {
        this.sectoresHijos = sectoresHijos;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
    
    public List<Sector> obtenerTotalSubSectores() {
        List<Sector> listaRecursion = new ArrayList<>();
        listaSectoresRecursiva(this, listaRecursion);
        return listaRecursion;
    }

    public void listaSectoresRecursiva(Sector sec, List<Sector> lista) {
        lista.add(sec);
        if (sec.getSectoresHijos() != null) {
            for (Sector secHijos : sec.getSectoresHijos()) {
                listaSectoresRecursiva(secHijos, lista);
            }
        }
    }
}
